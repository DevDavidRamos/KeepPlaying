package com.example.keepplaying.firebase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.keepplaying.firebase.data.local.dao.FavoritesDao
import com.example.keepplaying.firebase.data.local.dao.ProductsDao
import com.example.keepplaying.firebase.data.local.dao.RecentSearchDao
import com.example.keepplaying.firebase.data.local.entity.FavoriteEntity
import com.example.keepplaying.firebase.data.local.entity.ProductEntity
import com.example.keepplaying.firebase.data.local.entity.RecentSearchEntity

@Database(entities = [ProductEntity::class, RecentSearchEntity::class, FavoriteEntity::class], version = 1)
abstract class EcommerceDatabase: RoomDatabase() {

    abstract fun productsDao(): ProductsDao
    abstract fun recentSearchDao(): RecentSearchDao
    abstract fun favoritesDao(): FavoritesDao
}