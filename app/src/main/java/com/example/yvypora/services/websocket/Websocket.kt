package com.example.yvypora.services.websocket

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.yvypora.constants.Constants
import com.example.yvypora.services.datastore.TokenStore

import io.socket.client.IO
import io.socket.client.Socket


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
        mSocket = IO.socket(Constants.WEBSOCKET_BASE_URL, options)
        return mSocket
    }
}