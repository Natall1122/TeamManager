package es.nlc.teammanager

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import es.nlc.teammanager.clases.Missatges
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirestoreManager {

    private val firestore by lazy { FirebaseFirestore.getInstance() }

    suspend fun addMissatge(note: Missatges): Boolean {
        return try {
            firestore.collection(MISS_COLLECTION).add(note).await()
            true
        }catch(e: Exception){
            false
        }
    }

    suspend fun getMissatgesFlow(): Flow<List<Missatges>> = callbackFlow{
        var missCollection: CollectionReference? = null
        try {
            missCollection = FirebaseFirestore.getInstance()
                .collection(MISS_COLLECTION)
            val subscription = missCollection?.addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    val missatges = mutableListOf<Missatges>()
                    snapshot.forEach{
                        missatges.add(
                            Missatges(
                                nom = it.get(MISS_NOM).toString(),
                                missatge = it.get(MISS_CONTENT).toString()
                            )
                        )
                    }
                    trySend(missatges)
                }
            }
            awaitClose { subscription?.remove() }

        } catch (e: Throwable) {
            close(e)
        }
    }

    companion object{
        const val MISS_COLLECTION = "conversa"
        const val MISS_NOM = "nom"
        const val MISS_CONTENT = "missatge"
    }
}