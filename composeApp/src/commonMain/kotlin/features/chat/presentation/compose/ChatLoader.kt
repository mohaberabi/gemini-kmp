package features.chat.presentation.compose

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ChatLoader(modifier: Modifier = Modifier) {


    CircularProgressIndicator(modifier = Modifier.size(20.dp))
}