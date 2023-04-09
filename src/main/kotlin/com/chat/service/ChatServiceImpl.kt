package com.chat.service

import com.chat.grpc.ChatServiceGrpc
import com.chat.grpc.ChatServiceOuterClass
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ChatServiceImpl : ChatServiceGrpc.ChatServiceImplBase() {
    private val log = LoggerFactory.getLogger(ChatServiceImpl::class.java)

    override fun ask(
        request: ChatServiceOuterClass.ChatRequest,
        responceObserver: StreamObserver<ChatServiceOuterClass.ChatResponse>,
    ) {
        log.info("For chat ${request.chatId} question ${request.question}")

        val answer = "My answer ${request.question}"

        val response: ChatServiceOuterClass.ChatResponse = ChatServiceOuterClass.ChatResponse
            .newBuilder()
            .setChatId(request.chatId)
            .setAnswer(answer)
            .build()

        responceObserver.onNext(response)
        log.info("For chat ${response.chatId} answer ${response.answer}")
        responceObserver.onCompleted()

    }
}