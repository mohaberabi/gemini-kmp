package core.data.network.service

import core.data.network.dto.ApiConst
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.math.log

class HttpClientFactory {


    fun build(): HttpClient {

        return HttpClient {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    },
                )
            }

            install(Logging) {

                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL

            }
            defaultRequest {
                contentType(ContentType.Application.Json)
//                header(ApiConst.AUTH_HEADER_KEY, ApiConst.AUTH_HEADER_VALUE)
            }
        }
    }
}

