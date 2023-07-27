package com.example.keepplaying.firebase.data.remote

import com.example.keepplaying.firebase.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {
    override suspend fun login(email: String, password: String): String {
        return try {
            var useUID = ""
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    useUID =  it.user?.uid ?: ""
                }
                .await()
            useUID
        }catch (e: Exception){
            ""

        }
    }

    override suspend fun signUp(nombre: String, apellido: String,email: String, password: String): String {
        return try {
            var useUID = ""
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    useUID =  it.user?.uid ?: ""
                }
                .await()
            useUID



        }catch (e: Exception){
            ""
        }
    }

}