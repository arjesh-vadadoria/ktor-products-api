package com.products.plugins

import com.products.routes.productRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        productRoute()
    }
}
