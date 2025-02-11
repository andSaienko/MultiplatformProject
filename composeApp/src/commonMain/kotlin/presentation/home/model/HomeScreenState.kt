package presentation.home.model

import data.model.Product

data class HomeScreenState(
    val products: List<Product> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = true
)
