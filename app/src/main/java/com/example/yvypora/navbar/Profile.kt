package com.example.yvypora.navbar

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.yvypora.EditProfileSreen
import com.example.yvypora.ProfileClient

@Composable
fun Profile() {
    val context = LocalContext.current
    Column(
        Modifier
            .clickable {
                val intent = Intent(context, ProfileClient()::class.java)
                context.startActivity(intent)
            }
    ) {}
}