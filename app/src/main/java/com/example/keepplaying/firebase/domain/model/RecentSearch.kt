package com.example.keepplaying.firebase.domain.model

import com.example.keepplaying.firebase.data.local.entity.RecentSearchEntity


data class RecentSearch(
    val query: String
)

fun RecentSearch.mapToRecentSearchEntity(): RecentSearchEntity {
    return RecentSearchEntity(query = query)
}

fun List<RecentSearch>.mapToRecentSearchListEntity(): List<RecentSearchEntity> {
    return this.map { it.mapToRecentSearchEntity() }
}