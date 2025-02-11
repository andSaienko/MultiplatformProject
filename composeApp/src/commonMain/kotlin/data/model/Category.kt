package data.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null,
    val creationAt: String? = null,
    val updatedAt: String? = null,
)
