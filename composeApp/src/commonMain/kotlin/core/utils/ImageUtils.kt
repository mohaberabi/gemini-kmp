package core.utils

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

object ImageUtils {


    @OptIn(ExperimentalEncodingApi::class)
    fun encodeImage(bytes: ByteArray): String {
        val encoded = Base64.encode(bytes, 0, bytes.size - 1)
        return encoded
    }
}