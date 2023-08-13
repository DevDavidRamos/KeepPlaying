package com.example.keepplaying.firebase.domain.repository

import kotlinx.coroutines.flow.Flow


interface FavoritesRepository {

    suspend fun saveFavorite(id: String)

    fun getFavoriteIds(): Flow<List<String>>

    suspend fun deleteFavorite(id: String)
}