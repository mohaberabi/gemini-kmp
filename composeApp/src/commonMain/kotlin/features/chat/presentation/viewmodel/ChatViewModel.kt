package features.chat.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.domain.model.ChatModel
import core.domain.model.MessageType
import core.domain.repository.ChatRepository
import core.utils.AppResult
import core.utils.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatRepository: ChatRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()
    fun onAction(action: ChatActions) {
        when (action) {
            is ChatActions.OnImagePicked -> onPickedImage(action.bytes)
            ChatActions.OnRemoveImage -> onRemovePickedImage()
            ChatActions.OnSendClick -> sendMessage()
            is ChatActions.OnTextChange -> onTextChanged(action.message)
            ChatActions.OnToggleSheet -> toggleSheet()
        }
    }

    private fun toggleSheet() = _state.update { it.copy(showSheet = !it.showSheet) }

    private fun onPickedImage(bytes: ByteArray?) = _state.update { it.copy(pickedImage = bytes) }

    private fun onTextChanged(message: String) = _state.update { it.copy(message = message) }

    private fun onRemovePickedImage() = _state.update { it.copy(pickedImage = null) }


    private fun sendMessage() {
        _state.update { it.copy(loading = true) }
        val type = if (_state.value.pickedImage != null) MessageType.IMAGE else MessageType.TEXT
        val chat = ChatModel(
            message = _state.value.message,
            type = type,
            bytes = _state.value.pickedImage
        )
        viewModelScope.launch {
            _state.update {
                it.copy(
                    messages = it.messages + chat,
                    message = "",
                    pickedImage = null,
                    showSheet = false
                )
            }
            when (val geminiMessage = chatRepository.sendMessage(chat)) {
                is AppResult.Done -> {
                    _state.update {
                        it.copy(
                            messages = it.messages + geminiMessage.data,
                            loading = false,
                        )
                    }
                }

                is AppResult.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            pickedImage = null,
                            messages = it.messages + ChatModel.errorResponse
                        )
                    }

                }
            }

        }
    }
}