package data.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val images: List<String> = emptyList(),
    val creationAt: String,
    val updatedAt: String,
    val category: Category,
)