package presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.ProductsRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.home.model.HomeScreenState


@OptIn(FlowPreview::class)
class HomeViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    products = productsRepository.getProducts(),
                    isLoading = false
                )
            }
        }
        viewModelScope.launch {
            _state.map { it.searchQuery }
                .distinctUntilChanged()
                .debounce(300L)
                .collectLatest { query ->
                    val products = if (query.isNotBlank()) {
                        productsRepository.getProductsByTitle(query)
                    } else {
                        productsRepository.getProducts()
                    }
                    _state.update {
                        it.copy(products = products)
                    }
                }
        }
    }

    fun search(title: String) {
        viewModelScope.launch {
            _state.update { it.copy(searchQuery = title) }
        }
    }
}