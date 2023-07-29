package com.example.keepplaying.firebase.di

import android.content.Context
import androidx.room.Room
import com.example.keepplaying.firebase.data.local.EcommerceDatabase
import com.example.keepplaying.firebase.data.local.RoomConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {


    @Provides
    @Singleton
    fun provideEcommerceDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, EcommerceDatabase::class.java, RoomConstants.ECOMMERCE_DATABASE).build()


    @Provides
    @Singleton
    fun provideProductsDao(db: EcommerceDatabase) = db.productsDao()

}