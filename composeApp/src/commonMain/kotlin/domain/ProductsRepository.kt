package domain

import data.model.Product

interface ProductsRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(id: String): Product
    suspend fun getProductsByTitle(title: String): List<Product>
}