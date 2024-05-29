import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import core.presentation.ChatTheme
import features.chat.presentation.screen.ChatScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import shared.ImagePicker

@Composable
@Preview
fun App(
    imagePicker: ImagePicker
) {


    ChatTheme {

        ChatScreenRoot(
            imagePicker = imagePicker,
        )
    }
}