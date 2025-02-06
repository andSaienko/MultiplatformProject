package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Product
import domain.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(listOf())
    val products = _products.asStateFlow()

    init {
        viewModelScope.launch {
            homeRepository.getProducts().collect { products ->
                _products.update {
                    it + products
                }
            }
        }
    }
}