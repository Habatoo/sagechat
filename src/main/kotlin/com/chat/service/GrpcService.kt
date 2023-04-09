package com.chat.service

import com.chat.properties.ChatProperty
import io.grpc.Server
import io.grpc.ServerBuilder
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GrpcService(
    private val chatProperty: ChatProperty,
    private val chatServiceImpl: ChatServiceImpl,
) {
    private val log = LoggerFactory.getLogger(GrpcService::class.java)

    init {
        serverRun()
    }

    fun serverRun() {
        log.info("Init Server")
        val server: Server = ServerBuilder.forPort(chatProperty.port.toInt())
            .addService(chatServiceImpl)
            .build()

        Runtime.getRuntime().addShutdownHook(Thread {
            server.shutdown()
            server.awaitTermination()
        })

        server.start()
        log.info("Server Init successful")

        server.awaitTermination()
    }
}
