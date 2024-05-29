package shared

import features.chat.presentation.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


actual val viewModelModule = module {
    viewModelOf(::ChatViewModel)
}