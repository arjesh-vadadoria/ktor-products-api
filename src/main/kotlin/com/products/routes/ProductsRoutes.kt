package com.products.routes

import com.products.db.productDao
import com.products.models.Product
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.productRoute() {
    route("/products") {

        //Mark: - get all products
        get {
            call.respond(HttpStatusCode.OK, productDao.allProducts())
        }

        //Mark: - get product
        get("/{id}") {
            val id = call.parameters["id"]?.toInt()
            id?.let {
                productDao.getProduct(it)?.let { product ->
                    call.respond(HttpStatusCode.OK, product)
                } ?: call.respond(HttpStatusCode.NotFound, "Product Not Found")
            } ?: call.respond(HttpStatusCode.NotFound, "Provide Product Id!!")
        }

        post {
            val product = call.receive<Product>()
            runCatching {
                productDao.addProduct(product)
            }.onSuccess { prdct ->
                prdct?.let {
                    call.respond(HttpStatusCode.Created, it)
                } ?:call.respond(HttpStatusCode.NotImplemented,"Error adding product!!")
            }.onFailure {
                call.respond(HttpStatusCode.BadRequest,it.message ?: "SQL Exception!!")
            }
        }
    }

}