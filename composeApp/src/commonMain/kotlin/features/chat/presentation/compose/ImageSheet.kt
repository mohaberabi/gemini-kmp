package features.chat.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import features.chat.presentation.viewmodel.ChatActions
import shared.rememberBitmapFromBytes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    imageBytes: ByteArray?,
    loading: Boolean = false,
    imageText: String = "",
    onImageTextChanged: (String) -> Unit,
    onSend: () -> Unit = {},
) {

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = {
            onDismiss()
        },
    ) {
        if (imageBytes != null) {
            val bitmap = rememberBitmapFromBytes(imageBytes)
            Image(
                bitmap = bitmap!!,
                modifier = Modifier.aspectRatio(16 / 9f).padding(16.dp),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }


        Spacer(Modifier.height(16.dp))
        MessageTextField(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            suffix = {

                if (loading) {
                    ChatLoader()
                } else {
                    Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                        Spacer(Modifier.width(8.dp))
                        CircleButton(
                            onClick = onSend
                        )

                    }
                }
            },
            placeHolder = "Attach anything with the image",
            value = imageText,
            onChanged = onImageTextChanged
        )
        Spacer(Modifier.height(16.dp))
    }
}