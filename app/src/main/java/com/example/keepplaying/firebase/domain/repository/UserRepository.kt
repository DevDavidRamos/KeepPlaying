package com.example.keepplaying.firebase.domain.repository

import com.example.keepplaying.firebase.domain.model.User

interface UserRepository {

    suspend fun createUser(user: User):Boolean

    suspend fun getUser(uid: String,nombre:String, apellido: String): User
}