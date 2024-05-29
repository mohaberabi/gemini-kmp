import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import shared.ImagePickerFactory
import shared.KoinInit

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInit().init()
    },
) {
    App(
        imagePicker = ImagePickerFactory(LocalUIViewController.current).createImagePicker()
    )
}