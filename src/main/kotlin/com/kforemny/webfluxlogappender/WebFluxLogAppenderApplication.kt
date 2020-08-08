package com.kforemny.webfluxlogappender

import com.kforemny.webfluxlogappender.appender.FluxLogger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@SpringBootApplication
class WebFluxLogAppenderApplication

fun main(args: Array<String>) {
    runApplication<WebFluxLogAppenderApplication>(*args)
}

@RestController
@RequestMapping
class Controller(private val fluxLogger: FluxLogger) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/events", produces = [MediaType.TEXT_EVENT_STREAM_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun getEvents(serverRequest: ServerHttpRequest): Flux<Any> {

        val userAgent = serverRequest.headers[org.springframework.http.HttpHeaders.USER_AGENT]?.first()
                ?: throw RuntimeException("User agent not present")

        return Flux.create { fluxLogger.registerFlux(userAgent, it) }
    }

    @GetMapping("/test")
    fun testEvents() = logger.info("Test Message ${System.currentTimeMillis()}")

}
