package com.example.yvypora.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class MaskCnpj() : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilterCnpj(text)
    }
}


fun maskFilterCnpj(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 15) text.text.substring(0..14) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i==2) out += "."
        if (i==5) out += "."
        if (i==8) out += "/"
        if (i==12) out += "-"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 13) return offset +1
            return 14

        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <=3) return offset
            if (offset <=15) return offset -1
            return 14
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}
