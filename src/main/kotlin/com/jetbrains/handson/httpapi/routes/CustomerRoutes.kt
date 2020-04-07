package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.models.*
import io.ktor.application.*
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.*


fun Route.customerRouting() {
    route("/customer") {
        get {
            if (customerStorage.size > 0) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id}") {
            val id = call.parameters["id"] ?: ""
            val customer = customerStorage.find { it.id.compareTo(id) == 0 }
            if (customer != null) {
                call.respond(customer)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
        post {
            val customer = call.receive<Customer>()
            // TODO - This shouldn't really be done in production as
            // we should be accessing a mutable list in a thread-safe manner.
            // However, in production code we wouldn't be using mutable lists as a database!
            customerStorage.add(customer)
            call.respondText("Customer stored correctly", status = HttpStatusCode.Accepted)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: ""
            if (customerStorage.removeIf { it.id == id }) {
                call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}


fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}


