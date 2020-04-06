package com.jetbrains.handson.httpapi.routes

import io.ktor.application.Application
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.routing


fun Route.invalidEndpointException() {
    get("/error") {
        throw InvalidEndpointException("500 Server Error")
    }
}


fun Application.registerErrorSimulationRoutes() {
    routing {
        invalidEndpointException()
    }
}