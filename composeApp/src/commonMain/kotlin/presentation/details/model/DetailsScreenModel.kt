package presentation.details.model

import data.model.Product

data class DetailsScreenModel(
    val product: Product? = null,
    val isLoading: Boolean = true
)
