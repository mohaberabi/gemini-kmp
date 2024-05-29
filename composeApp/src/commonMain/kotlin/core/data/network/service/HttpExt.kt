package core.data.network.service

import DataError
import core.utils.AppResult
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url


suspend inline fun <reified Response : Any?> HttpClient.get(

    route: String,
    params: Map<String, Any?> = mapOf()
): AppResult<Response, DataError.Network> {

    return safeCall {
        get {
            url(route)
            params.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}


suspend inline fun <reified Request, reified Response : Any> HttpClient.post(
    route: String,
    body: Request
): AppResult<Response, DataError.Network> {

    return safeCall {
        post {
            url(route)
            setBody(body)
        }
    }
}

suspend inline fun <reified Response : Any> HttpClient.delete(
    route: String,
    queryParams: Map<String, Any?> = mapOf()
): AppResult<Response, DataError.Network> {
    return safeCall {
        delete {
            url(route)
            queryParams.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}