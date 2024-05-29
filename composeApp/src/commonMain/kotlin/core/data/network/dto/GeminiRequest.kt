package core.data.network.dto

import core.domain.model.ChatModel
import core.domain.model.MessageType
import core.utils.ImageUtils
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeminiRequest(
    val contents: List<Content>
) {
    companion object {
        fun fromDomain(chat: ChatModel): GeminiRequest {
            val content: List<Content> = when (chat.type) {
                MessageType.IMAGE -> {
                    val encoded = ImageUtils.encodeImage(chat.bytes!!)
                    listOf(
                        Content(
                            parts = listOf(
                                Part(text = chat.message),
                                Part(inlineData = InlineData(data = encoded))
                            )
                        ),
                    )
                }

                MessageType.TEXT -> listOf(
                    Content(parts = listOf(Part(chat.message)))
                )
            }
            val request = GeminiRequest(
                contents = content
            )
            return request
        }
    }
}


@Serializable
data class Content(
    val parts: List<Part>
)

@Serializable
data class InlineData(
    @SerialName("mime_type")
    val mimType: String = "image/jpeg",
    val data: String
)

@Serializable
data class Part(
    val text: String? = null,
    val inlineData: InlineData? = null
)