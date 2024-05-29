package core.domain.model


enum class MessageType {
    TEXT, IMAGE,
}

data class ChatModel(
    val id: Long? = null,
    val message: String,
    val isMine: Boolean = true,
    val bytes: ByteArray? = null,
    val type: MessageType,
) {
    companion object {
        val errorResponse = ChatModel(
            id = null,
            message = "Sorry i could not help you , please try again ",
            isMine = false,
            type = MessageType.TEXT
        )
    }

}

