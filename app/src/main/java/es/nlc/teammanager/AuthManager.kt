package es.nlc.teammanager

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

    fun getCurrentUser(): FirebaseUser?{
        return auth.currentUser

    }
}