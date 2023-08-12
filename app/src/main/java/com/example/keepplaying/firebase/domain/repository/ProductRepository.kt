package com.example.keepplaying.firebase.domain.repository

import com.example.keepplaying.firebase.domain.model.Product
import com.example.keepplaying.utils.Resource

interface ProductRepository {

    suspend fun fetchAllProducts(): Resource<List<Product>>

    suspend fun getAllProductsCache(): List<Product>

    suspend fun insertAllProductsCache(products: List<Product>, favorites: List<String>)

    suspend fun updateAllProductsCache(products: List<Product>, favorites: List<String>)
}