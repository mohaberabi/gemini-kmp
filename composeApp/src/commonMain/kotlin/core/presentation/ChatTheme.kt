package core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun ChatTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        content = content,
        colorScheme = lightColorScheme(
            primary = Color.Black,
            secondary = Color.Gray,
            onPrimary = Color.White
        )
    )

}