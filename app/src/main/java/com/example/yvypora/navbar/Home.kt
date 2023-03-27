package com.example.yvypora.navbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable

fun Home(){
    Column() {
        Text(
            text = "HOME",
            style = MaterialTheme.typography.h1
        )
    }
}