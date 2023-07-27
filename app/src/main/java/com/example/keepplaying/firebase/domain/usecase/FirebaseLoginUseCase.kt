package com.example.keepplaying.firebase.domain.usecase

import com.example.keepplaying.firebase.domain.model.User
import com.example.keepplaying.firebase.domain.repository.AuthRepository
import com.example.keepplaying.firebase.domain.repository.UserRepository
import com.example.keepplaying.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(email: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading)
        val userUID = authRepository.login(email,password)
        if (userUID.isNotEmpty()) {

            val user = userRepository.getUser(uid = userUID, nombre = "", apellido = "")

            emit(Resource.Success(user))
            emit(Resource.Finished)
        } else {
            emit(Resource.Error("Login error"))
            emit(Resource.Finished)
        }
    }
}