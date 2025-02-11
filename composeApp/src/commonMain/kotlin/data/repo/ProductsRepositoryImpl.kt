package data.repo

import data.model.Product
import domain.ProductsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductsRepositoryImpl(
    private val httpClient: HttpClient
) : ProductsRepository {

    private val baseUrl = "https://api.escuelajs.co/api/v1"

    override suspend fun getProducts(): List<Product> {
        return httpClient.get("$baseUrl/products").body()
    }

    override suspend fun getProductById(id: String): Product {
        return httpClient.get("$baseUrl/products/$id").body()
    }

    override suspend fun getProductsByTitle(title: String): List<Product> {
        return httpClient.get("$baseUrl/products") {
            url {
                parameters.append("title", title)
            }
        }.body()
    }
}