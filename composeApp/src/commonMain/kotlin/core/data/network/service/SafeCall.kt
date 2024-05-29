package core.data.network.service

import DataError
import core.utils.AppResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException


suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): AppResult<T, DataError.Network> {

    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return AppResult.Error(DataError.Network.NO_NETWORK)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return AppResult.Error(DataError.Network.SERIALIZATION_ERROR)
    } catch (e: Exception) {
        e.printStackTrace()
        return AppResult.Error(DataError.Network.UNKNOWN_ERROR)
    }
    return mapResponseToAppResult(response)
}

suspend inline fun <reified T> mapResponseToAppResult(response: HttpResponse): AppResult<T, DataError.Network> {

    return when (response.status.value) {
        in 200..299 -> AppResult.Done(response.body<T>())
        401 -> AppResult.Error(DataError.Network.UNAUTHORIZED)
        408 -> AppResult.Error(DataError.Network.REQUEST_TIMEOUT)
        409 -> AppResult.Error(DataError.Network.CONFLICT)
        413 -> AppResult.Error(DataError.Network.PAYLOAD_TOO_LARGE)
        429 -> AppResult.Error(DataError.Network.TOO_MANY_REQUEST)
        in 500..599 -> AppResult.Error(DataError.Network.SERVER_ERROR)
        else -> AppResult.Error(DataError.Network.UNKNOWN_ERROR)
    }
}


