package com.starlight.module.uicore.utils

import android.content.Context
import android.media.MediaScannerConnection
import android.os.Environment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File


//fun formatFileSize(size: Float, context: Context): String {
//    var folderSize = size
//    var unit = context.getStringArray(R.array.memory_unit)[0]
//    val oneGbFromB = 1_073_741_824.0f
//    val oneMBFromB = 1_048_576.0f
//
//    // >1MB dv = MB, <1MB dv = KB
//    if (folderSize >= oneMBFromB && folderSize < oneGbFromB) {
//        folderSize /= oneMBFromB
//        unit = context.getStringArray(R.array.memory_unit)[1]
//    } else if (folderSize >= oneGbFromB) {
//        folderSize /= oneGbFromB
//        unit = context.getStringArray(R.array.memory_unit)[2]
//    } else {
//        folderSize /= 1024
//    }
//
//    return (folderSize.decimalFormat() + unit)
//}

fun copyToExternal(
    context: Context,
    pathCache: String,
    path: String,
    mediaType: String? = null,
    success: () -> Unit,
    error: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val input = File(pathCache)
        val output = File(path)
        try {
            input.copyTo(output)
            input.delete()
            withContext(Dispatchers.Main) {
                success()
            }
            MediaScannerConnection.scanFile(
                context,
                arrayOf(output.path),
                arrayOf(mediaType),
                null
            );
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                error()
            }
        }
    }
}

fun File.copyTo(file: File) {
    inputStream().use { input ->
        file.outputStream().use { output ->
            input.copyTo(output)
        }
    }
}

// internal storage
fun getSaveDir(context: Context, folderName: String): String {
    return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        .toString() + "/$folderName"
}
