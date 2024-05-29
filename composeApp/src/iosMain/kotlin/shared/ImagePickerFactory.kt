package shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.UIKit.UIViewController

actual class ImagePickerFactory(
    private val root: UIViewController,
) {

    @Composable
    actual fun createImagePicker(): ImagePicker {
        return remember {
            ImagePicker(root)
        }
    }

}