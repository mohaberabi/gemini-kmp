package org.mohaberabi.geminichat

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import shared.ImagePicker
import shared.ImagePickerFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val imagePicker = ImagePickerFactory().createImagePicker()

            App(
                imagePicker = imagePicker,
            )

        }
    }
}
