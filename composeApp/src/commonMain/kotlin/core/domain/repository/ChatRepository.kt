package core.domain.repository

import DataError
import core.domain.model.ChatModel
import core.utils.AppResult

interface ChatRepository {


    suspend fun sendMessage(message: ChatModel): AppResult<ChatModel, DataError.Network>
}