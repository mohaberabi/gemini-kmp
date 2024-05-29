package shared

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

actual class ImagePicker(
    private val activity: ComponentActivity,
) {

    companion object {
        const val TYPE = "image/*"
    }

    private lateinit var launcher: ActivityResultLauncher<String>

    @Composable
    actual fun RegisterImagePicker(onImagePicked: (ByteArray) -> Unit) {

        launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {

                uri ->
            uri?.let {

                activity.contentResolver.openInputStream(uri)?.use { stream ->
                    val bytes = stream.readBytes()
                    onImagePicked(bytes)
                }
            }
        }
    }


    actual fun pickupImage() = launcher.launch(TYPE)
}