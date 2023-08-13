package com.example.keepplaying.firebase.domain.usecase

import com.example.keepplaying.firebase.domain.model.RecentSearch
import com.example.keepplaying.firebase.domain.repository.RecentSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SaveRecentSearchUseCase @Inject constructor(
    private val repository: RecentSearchRepository
) {

    suspend operator fun invoke(recentSearch: RecentSearch) {
        return withContext(Dispatchers.IO) {
            repository.insertRecentSearch(recentSearch)
        }
    }
}