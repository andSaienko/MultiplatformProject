package presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.repo.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import presentation.details.model.DetailsScreenModel

class DetailsViewModel(
    private val productsRepository: ProductsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.get<String>("itemId")

    private val _state = MutableStateFlow(DetailsScreenModel())
    val state = _state
        .onStart {
            _state.update {
                it.copy(
                    product = productsRepository.getProductById(checkNotNull(id)),
                    isLoading = false
                )
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            DetailsScreenModel()
        )
}