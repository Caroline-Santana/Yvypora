package com.example.yvypora.utils

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.io.File
import java.io.FileOutputStream

class ImagesChoose : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilterCpf(text)
    }
}

private fun PermissaoGaleria(){

}


fun createImageFileFromUri(imageUri: Uri, context: Context): File {
    val file = File(imageUri.path!!)
    val extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(context.contentResolver.getType(imageUri))
    val fileName = "${System.currentTimeMillis()}.$extension"
    val destinationFile = File(context.cacheDir, fileName)
    try {
        val source = context.contentResolver.openInputStream(imageUri)
        val destination = FileOutputStream(destinationFile)
        source!!.copyTo(destination)
        source.close()
        destination.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return destinationFile
}


