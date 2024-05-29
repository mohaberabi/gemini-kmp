package core.data.network.source

import core.data.network.dto.ApiConst
import core.data.network.dto.Content
import core.data.network.dto.GeminiRequest
import core.data.network.dto.GeminiResponse
import core.data.network.dto.toDomain
import core.data.network.service.post
import core.domain.model.ChatModel
import core.domain.model.MessageType
import core.domain.source.ChatRemoteDataSource
import core.utils.AppResult
import core.utils.ImageUtils
import io.ktor.client.HttpClient

class KtoChatRemoteDataSource(
    private val httpClient: HttpClient,
) : ChatRemoteDataSource {
    override suspend fun sendMessage(
        message: ChatModel,
    ): AppResult<ChatModel, DataError.Network> {
        val route = ApiConst.constructUrl()
        val result = httpClient.post<GeminiRequest, GeminiResponse>(
            route,
            GeminiRequest.fromDomain(message)
        )
        return when (result) {
            is AppResult.Done -> AppResult.Done(result.data.toDomain())
            is AppResult.Error -> AppResult.Error(result.error)
        }

    }
}