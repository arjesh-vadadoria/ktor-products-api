package com.products.db

import com.products.models.Product
import com.products.models.User

interface UserService {
    suspend fun allUser(): List<User>
    suspend fun getUser(id: Int): User
    suspend fun addUser(
        name: String,
        description: String,
        price: Double,
        image: String,
    ): User

    suspend fun editUser(
        id: Int, name: String,
        description: String,
        price: Double,
        image: String,
    ): User

    suspend fun removeUser(id: Int): User
}