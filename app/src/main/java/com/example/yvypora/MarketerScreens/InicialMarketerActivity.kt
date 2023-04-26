package com.example.yvypora.MarketerScreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.yvypora.ui.theme.YvyporaTheme

class InicialMarketerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YvyporaTheme {


            }
        }
    }
}