package com.example.keepplaying.firebase.domain.usecase

import com.example.keepplaying.firebase.domain.model.User
import com.example.keepplaying.firebase.domain.repository.AuthRepository
import com.example.keepplaying.firebase.domain.repository.UserRepository
import com.example.keepplaying.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository

) {

    suspend operator fun invoke(nombre:String, apellido:String, email:String, password: String): Flow<Resource<Boolean>> = flow{

        emit(Resource.Loading)
        val userUID : String = authRepository.signUp(nombre, apellido, email, password)
        if (userUID.isNotEmpty())
        {
            userRepository.createUser(
                User(
                nombre = nombre,
                apellido = apellido,
                email = email,
                uid = userUID
            )
            )

            emit(Resource.Success(true))
        }else{
            emit(Resource.Error("SignUp Error"))
        }

    }


}