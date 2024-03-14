package com.products.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

@Serializable
data class Product(
    val id: Int = 0,
    val name: String,
    val description: String,
    val price: Double,
)

object Products : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val description = varchar("description", 1024)
    val price = double("price")

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}