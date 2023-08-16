package com.example.keepplaying.presentation.home_menu.card_zone.xbox

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepplaying.firebase.domain.model.Product
import com.example.keepplaying.firebase.domain.repository.FavoritesRepository
import com.example.keepplaying.firebase.domain.usecase.GetProductsByNameQueryUseCase
import com.example.keepplaying.firebase.domain.usecase.GetXboxProductsUseCase
import com.example.keepplaying.utils.UiState
import com.example.keepplaying.utils.mapToUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class XboxViewModel  @Inject constructor(
    private val getXboxProductsUseCase: GetXboxProductsUseCase,
    private val favoritesRepository: FavoritesRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _productsFlow = MutableStateFlow<UiState<List<Product>>?>(null)
    val productsFlow = _productsFlow.asStateFlow()

    private val query: String = "Xbox"

    init {

        getXboxProducts(query)
    }

    private fun getXboxProducts(queryName: String) {
        viewModelScope.launch {
            _productsFlow.value = UiState.Loading

            getXboxProductsUseCase(query = queryName).onEach {
                _productsFlow.value = it.mapToUiState()
            }.launchIn(viewModelScope)
        }
    }

    fun saveFavorite(id: String) {
        viewModelScope.launch {
            favoritesRepository.saveFavorite(id)
        }
    }

    fun deleteFavorite(id: String) {
        viewModelScope.launch {
            favoritesRepository.deleteFavorite(id)
        }
    }

}