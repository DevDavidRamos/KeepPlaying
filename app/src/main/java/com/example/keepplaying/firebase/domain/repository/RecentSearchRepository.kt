package com.example.keepplaying.firebase.domain.repository

import com.example.keepplaying.firebase.domain.model.RecentSearch
import kotlinx.coroutines.flow.Flow

interface RecentSearchRepository {

    suspend fun getAllRecentSearch(): Flow<List<RecentSearch>>

    suspend fun insertRecentSearch(recentSearch: RecentSearch)
}