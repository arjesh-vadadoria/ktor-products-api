package com.products.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

@Serializable
data class UserDetails(
    val id: Int,
    val name: String,
    val wishListedProducts: ArrayList<Product>,
)