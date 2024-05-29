package core.data.network.dto

import core.domain.model.ChatModel
import core.domain.model.MessageType


fun GeminiResponse.toDomain(): ChatModel {


    val message = candidates.firstOrNull()?.let { cand ->
        cand.content.parts.firstOrNull()?.let { part ->
            part.text
        }
    } ?: "Sorry i could not find any answer"
    val chat = ChatModel(
        isMine = false,
        bytes = null,
        message = message,
        type = MessageType.TEXT
    )

    return chat
}