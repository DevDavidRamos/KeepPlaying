package com.example.keepplaying.firebase.data.repository

import com.example.keepplaying.firebase.data.local.dao.ProductsDao
import com.example.keepplaying.firebase.data.local.entity.mapToProductList
import com.example.keepplaying.firebase.data.remote.FirestoreConstants.PRODUCTS_COLLECTION
import com.example.keepplaying.firebase.domain.model.Product
import com.example.keepplaying.firebase.domain.model.mapToProductEntityList
import com.example.keepplaying.firebase.domain.repository.ProductRepository
import com.example.keepplaying.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val productsDao: ProductsDao,
    private val firestoreInstance: FirebaseFirestore
): ProductRepository {

    override suspend fun fetchAllProducts(): Resource<List<Product>> {
        return try {
            val productList = firestoreInstance.collection(PRODUCTS_COLLECTION)
                .get()
                .await()
                .toObjects(Product::class.java)

            Resource.Success(productList)
        } catch (e: Exception) {
            Resource.ErrorConnection(e)
        }
    }

    override suspend fun getAllProductsCache(): List<Product> {
        return withContext(Dispatchers.IO) {
            productsDao.getAllProducts().mapToProductList()
        }
    }

    override suspend fun insertAllProductsCache(products: List<Product>, favorites: List<String>) {
        productsDao.insertAllProducts(products.mapToProductEntityList(favorites))
    }

    override suspend fun updateAllProductsCache(products: List<Product>, favorites: List<String>) {
        withContext(Dispatchers.IO) {
            productsDao.updateAllProducts(products.mapToProductEntityList(favorites))
        }
    }

}