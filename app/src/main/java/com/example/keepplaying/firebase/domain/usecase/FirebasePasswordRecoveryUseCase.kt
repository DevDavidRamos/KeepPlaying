package com.example.keepplaying.firebase.domain.usecase

import com.example.keepplaying.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebasePasswordRecoveryUseCase @Inject constructor(

) {
    suspend operator fun invoke(email: String): Flow<Resource<Boolean>> = flow {

        try {
            emit(Resource.Loading)
            var isSuccessful = false


            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { isSuccessful = it.isSuccessful }
                .await()
            emit(Resource.Success(isSuccessful))
            emit(Resource.Finished)
        }catch (e:Exception){
            emit(Resource.Error(error(e.message.toString())))
            emit(Resource.Finished)

        }
    }

}