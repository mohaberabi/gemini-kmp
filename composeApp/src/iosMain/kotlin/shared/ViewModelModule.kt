package shared

import features.chat.presentation.viewmodel.ChatViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


actual val viewModelModule = module {

    singleOf(::ChatViewModel)
}