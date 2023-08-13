package com.example.keepplaying.firebase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.keepplaying.firebase.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favoriteentity")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Query("DELETE FROM favoriteentity WHERE id=:id")
    suspend fun deleteFavorite(id: String)
}