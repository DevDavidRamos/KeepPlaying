package com.example.keepplaying.presentation.home_menu.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepplaying.firebase.domain.model.Product
import com.example.keepplaying.firebase.domain.usecase.GetAllProductsUseCase
import com.example.keepplaying.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
): ViewModel() {

    private val _products: MutableLiveData<Resource<List<Product>>> = MutableLiveData()
    val products: LiveData<Resource<List<Product>>>
        get() = _products

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            val productsList = getAllProductsUseCase()
            _products.postValue(productsList)
        }
    }

}