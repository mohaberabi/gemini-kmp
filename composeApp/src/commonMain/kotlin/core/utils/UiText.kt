package core.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
sealed class UiText {
    data class DynamicString(
        val value: String
    ) : UiText()

    data class StringRes constructor(
        val res: StringResource,
        val args: List<Any> = listOf()
    ) : UiText()


    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringRes -> {
                stringResource(
                    res,
                    args
                )
            }
        }
    }


}