package features.chat.presentation.viewmodel

import core.utils.UiText


sealed interface ChatEvent {

    data class Error(val error: UiText) : ChatEvent
}