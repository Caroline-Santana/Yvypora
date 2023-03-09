package com.example.yvypora.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation


class MaskCpf() : VisualTransformation {

        override fun filter(text: AnnotatedString): TransformedText {
            return maskFilter(text)
        }
    }


    fun maskFilter(text: AnnotatedString): TransformedText {

        val trimmed = if (text.text.length >= 11) text.text.substring(0..10) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i==2) out += "."
            if (i==5) out += "."
            if (i==8) out += "-"
        }

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 10) return offset +1
                return 11

            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <=3) return offset
                if (offset <=12) return offset -1
                return 11
            }
        }

        return TransformedText(AnnotatedString(out), numberOffsetTranslator)
    }
