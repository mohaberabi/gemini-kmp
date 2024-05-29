package features.chat.presentation.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MessageTextField(
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    label: String = "",
    onChanged: (String) -> Unit = {},
    value: String = "",
    suffix: @Composable () -> Unit = {},
    supportingText: String = "",

    ) {
    OutlinedTextField(
        enabled = true,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.fillMaxWidth(),
        value = value,
        trailingIcon = suffix,
        onValueChange = onChanged,
        supportingText = {
            Text(supportingText)
        },
        label = {
            Text(label)
        },
        placeholder = {
            Text(placeHolder)
        }
    )

}