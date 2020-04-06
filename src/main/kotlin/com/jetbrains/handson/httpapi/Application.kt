package com.jetbrains.handson.httpapi

import com.jetbrains.handson.httpapi.routes.*
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.serialization.json


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    install(StatusPages) {
        exception<InvalidEndpointException> {
            call.respond(HttpStatusCode.ServiceUnavailable, "Server performed invalid operation")
        }

    }
    registerCustomerRoutes()
    registerOrderRoutes()
    registerErrorSimulationRoutes()
}
