package com.example.yvypora.navbar

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.yvypora.FairsActivity

@Composable
fun Fair() {
    val context = LocalContext.current
    Column(Modifier. fillMaxSize()
        .clickable {
            val intent = Intent(context, FairsActivity::class.java)
            context.startActivity(intent)
        }) {
        Text(
            text = "Fair",
            style = MaterialTheme.typography.h1
        )
    }
}