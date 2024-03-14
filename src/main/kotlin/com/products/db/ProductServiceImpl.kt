package com.products.db

import com.products.models.Product
import com.products.models.Products
import com.products.plugins.dbQuery
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ProductServiceImpl : ProductService {

    private fun resultRowToProduct(row: ResultRow): Product = Product(
        id = row[Products.id],
        name = row[Products.name],
        description = row[Products.description],
        price = row[Products.price],
    )

    override suspend fun allProducts(): List<Product> = dbQuery {
        Products.selectAll().map(::resultRowToProduct)
    }

    override suspend fun getProduct(id: Int): Product? = dbQuery {
        Products.select { Products.id eq id }.map(::resultRowToProduct).singleOrNull()
    }

    override suspend fun addProduct(product: Product): Product? = dbQuery {
        val insertStatement = Products.insert {
            it[name] = product.name
            it[description] = product.description
            it[price] = product.price
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToProduct)
    }

    override suspend fun editProduct(product: Product): Boolean = dbQuery {
        Products.update({ Products.id eq product.id }) {
            it[name] = product.name
            it[description] = product.description
            it[price] = product.price
        } > 0
    }

    override suspend fun removeProduct(id: Int): Boolean = dbQuery {
        Products.deleteWhere { Products.id eq id } > 0
    }
}

val productDao: ProductService = ProductServiceImpl().apply {
    val product = Product(name = "Lanny", description = "accusata sdf", price = 4.5)
    val product1 = Product(name = "Lanny1", description = "accusata sdf1", price = 4.5)
    runBlocking {
        if (allProducts().isEmpty()) {
            addProduct(product)
            addProduct(product1)
            addProduct(product)
            addProduct(product1)
            addProduct(product)
            addProduct(product1)
            addProduct(product)
            addProduct(product1)
        }
    }
}