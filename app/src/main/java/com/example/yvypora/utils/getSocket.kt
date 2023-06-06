package com.example.yvypora.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.yvypora.services.websocket.Websocket
import io.socket.client.Socket

@Composable
fun getSocket(): Socket {
    val context = LocalContext.current
    val socket = Websocket().getInstance(context)
    socket.connect()
    return socket
}