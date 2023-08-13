package com.example.keepplaying.firebase.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity(
    @PrimaryKey
    val id: String
)