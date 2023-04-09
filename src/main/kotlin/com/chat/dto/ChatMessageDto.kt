package com.chat.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ChatMessageDto(
    val chatId: Long,
    var answer: String,
)
