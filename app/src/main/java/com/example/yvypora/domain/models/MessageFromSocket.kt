package com.example.yvypora.domain.models

import java.util.Date

data class MessageFromSocket(
    var id: String? = null,
    var from: Int? = null,
    var to: Int? = null,
    var fromName: String? = null,
    var toName: String? = null,
    val content: String,
    var senderName: String? = null,
    var senderId: Int? = null,
    var toId: Int? = null,
    var fromId: Int? = null,
    var createdAt: String? = null
)
data class MessageReceivedFromSocket(
    val from: Int,
    val content: String,
)