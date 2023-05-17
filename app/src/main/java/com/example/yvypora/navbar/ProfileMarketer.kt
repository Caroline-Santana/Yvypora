package com.example.yvypora.navbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.yvypora.MarketerScreens.JoiningFieldsMarketer
import com.example.yvypora.ScreenClients.HeaderProfile
import com.example.yvypora.ScreenClients.JoiningFields

@Composable
fun ProfileMarketer(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeaderProfile()
        JoiningFieldsMarketer()
    }
}