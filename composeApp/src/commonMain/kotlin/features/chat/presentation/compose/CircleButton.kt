package features.chat.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector = Icons.AutoMirrored.Filled.Send,
    enabledColor: Color = MaterialTheme.colorScheme.primary,
    disabledColor: Color = Color.Gray,
    onClick: () -> Unit = {},
    size: Dp = 26.dp
) {

    val color = if (enabled) enabledColor else disabledColor
    Icon(
        icon,
        modifier = modifier
            .clip(CircleShape)
            .background(color).clickable { onClick() }
            .size(size).padding(4.dp),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onPrimary,
    )
}