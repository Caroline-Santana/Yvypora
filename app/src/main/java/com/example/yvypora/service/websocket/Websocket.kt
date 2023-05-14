package com.example.yvypora.service.websocket

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.yvypora.service.datastore.TokenStore

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException


class Websocket {
    companion object {
        private lateinit var mSocket: Socket

    }

    @Composable
    fun getInstance(context: Context): Socket {
        if (mSocket.isActive) return mSocket
        val options = IO.Options()
        val jwtToken = TokenStore(context).getToken.collectAsState(initial = "")
        options.query = "token=$jwtToken"
        mSocket = IO.socket("http://localhost:3337", options)
        return mSocket
    }
}