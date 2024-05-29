package features.chat.presentation.viewmodel

import core.domain.model.ChatModel

data class ChatState(
    val messages: List<ChatModel> = listOf(),
    val message: String = "",
    val pickedImage: ByteArray? = null,
    val loading: Boolean = false,
    val showSheet: Boolean = false
)
