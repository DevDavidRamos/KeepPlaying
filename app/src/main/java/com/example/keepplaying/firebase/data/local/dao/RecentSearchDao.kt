package com.example.keepplaying.firebase.data.local.dao


import com.example.keepplaying.firebase.data.local.entity.RecentSearchEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchDao {

    @Query("SELECT * FROM recentsearchentity")
    fun getAllRecentSearch(): Flow<List<RecentSearchEntity>>

    @Insert(onConflict = REPLACE)
    fun insertRecentSearch(recentSearchEntity: RecentSearchEntity)
}