package com.products.db

import com.products.models.Product

interface ProductService {
    suspend fun allProducts(): List<Product>
    suspend fun getProduct(id: Int): Product?
    suspend fun addProduct(
        product: Product,
    ): Product?

    suspend fun editProduct(
        product: Product,
    ): Boolean

    suspend fun removeProduct(id: Int): Boolean
}