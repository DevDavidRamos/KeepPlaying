package com.example.keepplaying.firebase.di

import com.example.keepplaying.firebase.data.remote.FirebaseAuthRepositoryImpl
import com.example.keepplaying.firebase.data.remote.FirestoreUserRepositoryImpl
import com.example.keepplaying.firebase.domain.repository.AuthRepository
import com.example.keepplaying.firebase.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepository: FirebaseAuthRepositoryImpl): AuthRepository


    @Binds
    abstract fun bindUserRepository(userRepository: FirestoreUserRepositoryImpl): UserRepository

}