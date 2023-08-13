package com.example.keepplaying.firebase.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.keepplaying.firebase.domain.model.RecentSearch


@Entity
data class RecentSearchEntity(
    @PrimaryKey
    val query: String
)

fun RecentSearchEntity.mapToRecentSearch(): RecentSearch {
    return RecentSearch(query = query)
}

fun List<RecentSearchEntity>.mapToRecentSearchList(): List<RecentSearch> {
    return this.map { it.mapToRecentSearch() }
}