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
    private val auth = AuthManager()

    suspend fun addMissatge(note: Missatges): Boolean {
        return try {
            firestore.collection(NOTE_COLLECTION).add(note).await()
            true
        }catch(e: Exception){
            false
        }
    }

    suspend fun getNotesFlow(): Flow<List<Missatges>> = callbackFlow{
        var notesCollection: CollectionReference? = null
        try {
            notesCollection = FirebaseFirestore.getInstance()
                .collection(NOTE_COLLECTION)
            val subscription = notesCollection?.addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    val notes = mutableListOf<Missatges>()
                    snapshot.forEach{
                        notes.add(
                            Missatges(
                                nom = it.get(NOTE_NOM).toString(),
                                missatge = it.get(NOTE_CONTENT).toString()
                            )
                        )
                    }
                    trySend(notes)
                }
            }
            awaitClose { subscription?.remove() }

        } catch (e: Throwable) {
            close(e)
        }
    }

    companion object{
        const val NOTE_COLLECTION = "conversa"
        const val NOTE_NOM = "nom"
        const val NOTE_CONTENT = "missatge"
    }
}