package es.nlc.teammanager

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthManager {
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    suspend fun login(email: String, password: String): FirebaseUser?{
        return try{
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            authResult.user

        }catch (e: Exception){
            null
        }
    }

    suspend fun signUp(email: String, password: String): FirebaseUser?{
        return try{
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            authResult.user
        }catch(e: Exception){
            null
        }
    }

    suspend fun remember(email: String): Unit{
        auth.sendPasswordResetEmail(email).await()
    }


    fun logOut(){
        auth.signOut()
    }

    // https://firebase.google.com/docs/auth/android/manage-users?hl=es-419
    fun deleteUser(): Boolean{
        val user = auth.currentUser!!
        user.delete()
        return true
    }

    fun getCurrentUser(): FirebaseUser?{
        return auth.currentUser
    }
    fun getEmail(): String? {
        return try {
            val user = auth.getCurrentUser()
            user?.email
        } catch (e: Exception) {
            null
        }
    }
    suspend fun setUsername(username: String): Boolean {
        val user = auth.currentUser
        user?.let { currentUser ->
            val userData = hashMapOf("username" to username)
            firestore.collection("users").document(currentUser.uid).set(userData).await()
            return true
        }
        return false
    }

    suspend fun getUsername(): String? {
        val user = auth.currentUser
        return user?.let { currentUser ->
            val userData = firestore.collection("users").document(currentUser.uid).get().await()
            userData.getString("username")
        }
    }

}