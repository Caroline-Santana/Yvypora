package com.example.yvypora.api

import android.content.Context
import android.util.Log
import com.example.yvypora.service.datastore.TokenStore
import io.socket.client.IO


class Socket(private val userToken: String) {
    private lateinit var io: io.socket.client.Socket;

    private val options = IO.Options().apply {
        query = "token${userToken}"
    }

    fun createIO() {
        try {
            this.io = IO.socket("http://localhost:3337", options)
        } catch(err: Error) {
            Log.i("teste", err.toString())
        }
    }

    fun connect() {
        this.io.connect()
    }

}


