package core.di

import core.data.network.service.HttpClientFactory
import core.data.network.source.KtoChatRemoteDataSource
import core.data.repository.DefaultChatRepository
import core.domain.repository.ChatRepository
import core.domain.source.ChatRemoteDataSource
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import shared.DispatchersProvider


val appModule = module {


    single<HttpClient> {
        HttpClientFactory().build()
    }
    single<ChatRemoteDataSource> {
        KtoChatRemoteDataSource(get())
    }

    single<ChatRepository> {
        DefaultChatRepository(get())
    }

 
}