package com.example.yvypora.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class MaskCep: VisualTransformation {

        override fun filter(text: AnnotatedString): TransformedText {
            return maskFilterCep(text)
        }
    }


    fun maskFilterCep(text: AnnotatedString): TransformedText {

        val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i==4) out += "-"

        }

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 4) return offset
                if (offset <= 7) return offset +1
                return 8

            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <=5) return offset
                if (offset <=9) return offset -1
                return 8
            }
        }

        return TransformedText(AnnotatedString(out), numberOffsetTranslator)
    }
