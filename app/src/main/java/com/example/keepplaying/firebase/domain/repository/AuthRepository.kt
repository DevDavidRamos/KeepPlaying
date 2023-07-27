package com.example.keepplaying.firebase.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): String
    suspend fun signUp(nombre: String, apellido: String,email:String, password: String): String


}