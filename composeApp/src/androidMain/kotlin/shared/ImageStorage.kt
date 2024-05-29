package shared

import android.content.Context
import kotlinx.coroutines.withContext
import java.util.UUID

actual class ImageStorage(
    private val context: Context,
    private val coroutineDispatcher: DispatchersProvider,
) {
    companion object {
        const val DEFAULT_EXT = ".jpg"
    }

    actual suspend fun saveImage(bytes: ByteArray): String {

        return withContext(coroutineDispatcher.io) {
            val fileName = UUID.randomUUID().toString() + DEFAULT_EXT
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { stream ->
                stream.write(bytes)
            }
            fileName
        }
    }


    actual suspend fun getImage(name: String): ByteArray? {
        return withContext(coroutineDispatcher.io) {
            context.openFileInput(name).use { stream ->
                stream.readBytes()
            }
        }
    }

    actual suspend fun deleteImage(name: String) {
        return withContext(coroutineDispatcher.io) {
            context.deleteFile(name)
        }
    }

}