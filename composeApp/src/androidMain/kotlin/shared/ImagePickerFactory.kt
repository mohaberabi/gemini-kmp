package shared

import android.media.Image
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual class ImagePickerFactory {
    @Composable
    actual fun createImagePicker(): ImagePicker {
        val context = LocalContext.current as ComponentActivity
        return remember {
            ImagePicker(context)
        }
    }
}