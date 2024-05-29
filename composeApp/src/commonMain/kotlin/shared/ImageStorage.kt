package shared

expect class ImageStorage {
    suspend fun saveImage(bytes: ByteArray): String
    suspend fun getImage(name: String): ByteArray?
    suspend fun deleteImage(name: String)
}