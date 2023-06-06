package com.example.yvypora.domain.models

import java.util.Date

data class MessageFromSocket(
    var id: String? = null,
    var from: Int? = null,
    var to: Int? = null,
    val fromName: String,
    val toName: String,

    val content: String,

    val toId: Int,
    val fromId: Int,
    var createdAt: String? = null
)

data class MessageReceivedFromSocket(
    val from: Int,
    val content: String,
)