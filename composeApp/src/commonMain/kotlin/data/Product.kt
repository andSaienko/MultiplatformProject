package data

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int? = null,
    val title: String? = null,
    val price: Int? = null,
    val description: String? = null,
    val images: List<String>? = null,
    val creationAt: String? = null,
    val updatedAt: String? = null,
    val category: Category? = null,
)