package com.example.yvypora.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class MaskPhone : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilterPhone(text)
    }
}


fun maskFilterPhone(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 10) text.text.substring(0..9) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i==0) out += "("
        if (i==1) out += ")"
        if (i==2) out += " "
        if (i==6) out += "("

    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 0) return offset
            if (offset <= 9) return offset +1
            return 10

        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <=1) return offset
            if (offset <=11) return offset -1
            return 10
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}
