package com.example.jetpackcompose.masterclass.performance.main_safety

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

class BitmapCompressor(
    private val context: Context
) {

    suspend fun compressImage(
        contentUri: Uri,
        compressionThreshold: Long
    ): Bitmap? {
        /*
        * Here Input stream and output stream are not suspending function but these are blocking function.
        * So its should be called in IO thread.
        * */
        return withContext(Dispatchers.IO){
            val inputBytes = context
                .contentResolver
                .openInputStream(contentUri)?.use { inputStream ->
                    inputStream.readBytes()
                } ?: return@withContext null

            val bitmap = BitmapFactory.decodeByteArray(inputBytes, 0, inputBytes.size)

            withContext(Dispatchers.Default){
                var outputBytes: ByteArray
                var quality = 100
                do {
                    ByteArrayOutputStream().use { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                        outputBytes = outputStream.toByteArray()
                        quality -= (quality * 0.1).roundToInt()
                    }
                } while (outputBytes.size > compressionThreshold && quality > 5)


                BitmapFactory.decodeByteArray(outputBytes, 0, outputBytes.size)
            }
        }
    }
}