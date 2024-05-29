package shared

import androidx.compose.runtime.Composable

expect class ImagePicker {


    @Composable

    fun RegisterImagePicker(onImagePicked: (ByteArray) -> Unit)


    fun pickupImage()
}