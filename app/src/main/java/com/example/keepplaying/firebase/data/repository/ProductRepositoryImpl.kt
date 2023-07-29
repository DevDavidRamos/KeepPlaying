package com.example.keepplaying.firebase.data.repository

import com.example.keepplaying.firebase.data.local.ProductsDao
import com.example.keepplaying.firebase.data.local.mapToProductList
import com.example.keepplaying.firebase.data.remote.FirestoreConstants.PRODUCTS_COLLECTION
import com.example.keepplaying.firebase.domain.model.Product
import com.example.keepplaying.firebase.domain.model.mapToProductEntityList
import com.example.keepplaying.firebase.domain.repository.ProductRepository
import com.example.keepplaying.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
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
            Resource.Error("Error")
        }
    }

    override suspend fun getAllProductsCache(): List<Product> {
        return productsDao.getAllProducts().mapToProductList()
    }

    override suspend fun insertAllProductsCache(products: List<Product>) {
        productsDao.insertAllProducts(products.mapToProductEntityList())
    }

}