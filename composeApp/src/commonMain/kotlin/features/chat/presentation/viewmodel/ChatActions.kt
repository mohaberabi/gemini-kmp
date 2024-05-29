package features.chat.presentation.viewmodel

sealed interface ChatActions {
    data class OnImagePicked(val bytes: ByteArray) : ChatActions
    data object OnRemoveImage : ChatActions
    data object OnSendClick : ChatActions
    data class OnTextChange(val message: String) : ChatActions
    data object OnToggleSheet : ChatActions
}