package com.kforemny.webfluxlogappender.appender

import ch.qos.logback.classic.spi.ILoggingEvent
import org.springframework.stereotype.Component
import reactor.core.publisher.FluxSink

@Component
class FluxLogger {
    private var logEventFluxSink = mutableMapOf<String, FluxSink<Any>>()

    fun registerFlux(userAgent: String, sink: FluxSink<Any>) {
        logEventFluxSink[userAgent] = sink
        sink.onCancel {
            println("do some stuff on cancel")
            logEventFluxSink.remove(userAgent, sink)
        }
        sink.onDispose {
            println("do smth on dispose")
            logEventFluxSink.remove(userAgent, sink)
        }
    }

    fun processAll(eventObject: ILoggingEvent) = logEventFluxSink.values.forEach { it.next(eventObject.formattedMessage) }
}