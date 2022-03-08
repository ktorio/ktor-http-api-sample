package com.jetbrains.handson.httpapi.model

val customerStorage= mutableListOf<Customer>()

@kotlinx.serialization.Serializable
data class Customer(
    val id: String,
    val firstName: String,
    val lastName: String
)
