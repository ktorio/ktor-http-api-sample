package com.jetbrains.handson.httpapi

import freemarker.cache.*
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.html.respondHtml
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

}
