package com.example.yvypora.navbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.yvypora.HeaderProfile
import com.example.yvypora.JoiningFields

@Composable
fun Profile(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeaderProfile()
        JoiningFields()
    }
}