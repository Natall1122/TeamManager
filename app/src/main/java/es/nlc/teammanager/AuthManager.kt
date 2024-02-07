package es.nlc.teammanager

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class AuthManager {
    private val auth: FirebaseAuth by lazy { Firebase.auth }

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
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, R.string.del.toString())
                }
            }
        return true
    }

    fun getCurrentUser(): FirebaseUser?{
        return auth.currentUser
    }
}