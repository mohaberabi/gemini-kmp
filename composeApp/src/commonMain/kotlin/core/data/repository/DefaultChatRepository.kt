package core.data.repository

import DataError
import core.domain.model.ChatModel
import core.domain.repository.ChatRepository
import core.domain.source.ChatRemoteDataSource
import core.utils.AppResult

class DefaultChatRepository(
    private val chatRemoteDataSource: ChatRemoteDataSource,
) : ChatRepository {
    override suspend fun sendMessage(message: ChatModel): AppResult<ChatModel, DataError.Network> =
        chatRemoteDataSource.sendMessage(message)
}