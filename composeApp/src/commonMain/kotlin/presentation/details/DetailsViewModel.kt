package presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.Product
import domain.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    private val productsRepository: ProductsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.get<String>("itemId")

    private val _product = MutableStateFlow<Product?>(null)
    val product = _product
        .onStart {
            _product.emit(productsRepository.getProductById(checkNotNull(id)))
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            null
        )
}