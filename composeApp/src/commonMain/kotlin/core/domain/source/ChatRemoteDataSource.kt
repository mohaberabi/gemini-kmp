package core.domain.source

import DataError
import core.domain.model.ChatModel
import core.utils.AppResult

interface ChatRemoteDataSource {


    suspend fun sendMessage(message: ChatModel): AppResult<ChatModel, DataError.Network>


}