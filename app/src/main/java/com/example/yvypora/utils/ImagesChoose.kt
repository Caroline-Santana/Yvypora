package com.example.yvypora.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ImagesChoose : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilterCpf(text)
    }
}

private fun PermissaoGaleria(){

}