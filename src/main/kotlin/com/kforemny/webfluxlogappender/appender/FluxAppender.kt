package com.kforemny.webfluxlogappender.appender

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.AppenderBase
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component


@Component
class FluxAppender : AppenderBase<ILoggingEvent>(), ApplicationContextAware {

    companion object {
        private var fluxLogger: FluxLogger? = null
    }

    override fun append(eventObject: ILoggingEvent) {
        fluxLogger?.processAll(eventObject)
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        fluxLogger = applicationContext.autowireCapableBeanFactory.getBean("fluxLogger") as FluxLogger
    }

}



