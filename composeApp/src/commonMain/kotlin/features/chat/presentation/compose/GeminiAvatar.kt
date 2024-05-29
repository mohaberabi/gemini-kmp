package features.chat.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun GeminiAvatar(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                "G",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(Modifier.width(16.dp))
        Text("Gemini")

    }
}