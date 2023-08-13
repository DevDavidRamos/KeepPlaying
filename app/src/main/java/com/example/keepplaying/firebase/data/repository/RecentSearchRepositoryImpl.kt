package com.example.keepplaying.firebase.data.repository

import com.example.keepplaying.firebase.data.local.dao.RecentSearchDao
import com.example.keepplaying.firebase.data.local.entity.mapToRecentSearchList
import com.example.keepplaying.firebase.domain.model.RecentSearch
import com.example.keepplaying.firebase.domain.model.mapToRecentSearchEntity
import com.example.keepplaying.firebase.domain.repository.RecentSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecentSearchRepositoryImpl @Inject constructor(
    private val recentSearchDao: RecentSearchDao
): RecentSearchRepository {

    override suspend fun getAllRecentSearch(): Flow<List<RecentSearch>> {
        return recentSearchDao.getAllRecentSearch().map { it.mapToRecentSearchList() }
    }

    override suspend fun insertRecentSearch(recentSearch: RecentSearch) {
        recentSearchDao.insertRecentSearch(recentSearch.mapToRecentSearchEntity())
    }

}