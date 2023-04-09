package com.chat.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("grpc")
class ChatProperty {
    lateinit var port: Integer
}