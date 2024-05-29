package features.chat.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.di.koinViewModel
import features.chat.presentation.compose.ChatBubble
import features.chat.presentation.compose.ChatLoader
import features.chat.presentation.compose.CircleButton
import features.chat.presentation.compose.GeminiAvatar
import features.chat.presentation.compose.ImageSheet
import features.chat.presentation.compose.MessageTextField
import features.chat.presentation.viewmodel.ChatState
import features.chat.presentation.viewmodel.ChatActions
import features.chat.presentation.viewmodel.ChatViewModel
import kotlinx.coroutines.launch
import shared.ImagePicker
import shared.rememberBitmapFromBytes


@Composable
fun ChatScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = koinViewModel(),
    imagePicker: ImagePicker
) {
    val state by viewModel.state.collectAsState()
    ChatScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction,
        imagePicker = imagePicker
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    state: ChatState,
    onAction: (ChatActions) -> Unit,
    imagePicker: ImagePicker
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val interactionSource = remember { MutableInteractionSource() }

    val keyboardController = LocalSoftwareKeyboardController.current

    imagePicker.RegisterImagePicker { bytes ->
        onAction(ChatActions.OnImagePicked(bytes))
        onAction(ChatActions.OnToggleSheet)
    }
    Scaffold(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            keyboardController?.hide()
        }.nestedScroll(scrollBehavior.nestedScrollConnection),

        bottomBar = {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            ) {
                MessageTextField(
                    modifier = Modifier
                        .padding(horizontal = 12.dp),
                    suffix = {

                        if (state.loading) {
                            ChatLoader()
                        } else {
                            Row(modifier = Modifier.padding(horizontal = 8.dp)) {

                                CircleButton(
                                    icon = Icons.Default.Add,
                                    onClick = {
                                        imagePicker.pickupImage()
                                    },
                                )
                                Spacer(Modifier.width(8.dp))
                                CircleButton(
                                    enabled = state.message.isNotEmpty(),
                                    onClick = {
                                        onAction(ChatActions.OnSendClick)
                                        keyboardController?.hide()


                                    },
                                )

                            }
                        }
                    },
                    placeHolder = "How can i help you ?",
                    value = state.message,

                    onChanged = {
                        onAction(ChatActions.OnTextChange(it))
                    }
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    GeminiAvatar()

                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.Bottom
            ) {
                items(
                    state.messages,
                ) { message ->
                    ChatBubble(
                        message
                    )
                }
            }


        }


    }

    if (state.showSheet)
        ImageSheet(
            imageBytes = state.pickedImage,
            onDismiss = {
                onAction(ChatActions.OnToggleSheet)
                onAction(ChatActions.OnRemoveImage)
            },
            onSend = { onAction(ChatActions.OnSendClick) },
            onImageTextChanged = { onAction(ChatActions.OnTextChange(it)) },
            loading = state.loading,
            imageText = state.message
        )
}