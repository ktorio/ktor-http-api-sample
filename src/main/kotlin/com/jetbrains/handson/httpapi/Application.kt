package com.jetbrains.handson.httpapi

import com.jetbrains.handson.httpapi.routes.registerCustomerRoutes
import com.jetbrains.handson.httpapi.routes.registerOrderRoutes
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.serialization.json


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    registerCustomerRoutes()
    registerOrderRoutes()
}
