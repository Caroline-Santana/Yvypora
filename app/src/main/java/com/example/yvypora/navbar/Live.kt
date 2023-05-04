package com.example.yvypora.navbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Live(){
    Column() {
        Text(
            text = "Live",
            style = MaterialTheme.typography.h1
        )
    }
}