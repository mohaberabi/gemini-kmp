package shared

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.refTo
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.withContext
import platform.Foundation.NSData
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSString
import platform.Foundation.NSUUID
import platform.Foundation.NSUserDomainMask
import platform.Foundation.create
import platform.Foundation.dataWithContentsOfFile
import platform.Foundation.getBytes
import platform.Foundation.stringByAppendingPathComponent
import platform.Foundation.writeToFile

actual class ImageStorage(
    private val dispatchersProvider: DispatchersProvider,
) {
    private val fileManager = NSFileManager.defaultManager

    companion object {
        const val DEFAULT_EXT = ".jpg"
    }

    private val docsDir = NSSearchPathForDirectoriesInDomains(
        directory = NSDocumentDirectory,
        domainMask = NSUserDomainMask,
        expandTilde = true
    ).first() as NSString

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    actual suspend fun saveImage(bytes: ByteArray): String {

        return withContext(dispatchersProvider.io) {
            val fileName = NSUUID.UUID().UUIDString + DEFAULT_EXT
            val fullPath = docsDir.stringByAppendingPathComponent(fileName)
            val data = bytes.usePinned {
                NSData.create(
                    bytes = it.addressOf(0),
                    length = bytes.size.toULong(),

                    )
            }
            data.writeToFile(
                path = fullPath,
                atomically = true
            )
            fullPath
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun getImage(name: String): ByteArray? {

        return withContext(dispatchersProvider.io) {
            memScoped {
                NSData.dataWithContentsOfFile(name)?.let { bytes ->
                    val array = ByteArray(bytes.length.toInt())
                    bytes.getBytes(array.refTo(0).getPointer(this), bytes.length)
                    return@withContext array
                }

            }
            return@withContext null

        }
    }

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun deleteImage(name: String) {

        withContext(dispatchersProvider.io) {

            fileManager.removeItemAtPath(name, null)
        }
    }
}