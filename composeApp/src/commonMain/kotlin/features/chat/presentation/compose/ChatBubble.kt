package features.chat.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.domain.model.ChatModel
import shared.rememberBitmapFromBytes

@Composable
fun ChatBubble(
    message: ChatModel,

    ) {
    val backgroundColor = if (message.isMine) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }

    val contentColor = if (message.isMine) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        Color.Black
    }

    val arrangement = if (message.isMine) {
        Arrangement.End
    } else {
        Arrangement.Start
    }


    val padding = if (message.isMine) {
        PaddingValues(start = 50.dp)
    } else {
        PaddingValues(end = 50.dp)

    }
    val photoModifier = Modifier.clip(RoundedCornerShape(16))

    Row(
        horizontalArrangement = arrangement,
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        ) {

            Column {
                if (message.bytes != null) {
                    val bitmap = rememberBitmapFromBytes(message.bytes)
                    Image(
                        bitmap = bitmap!!,
                        modifier = photoModifier,
                        contentDescription = "chat image"
                    )
                }
                if (message.message.isNotEmpty())
                    Text(
                        text = message.message,
                        style = MaterialTheme.typography.bodyMedium.copy(color = contentColor),
                    )
            }
        }
    }

}
