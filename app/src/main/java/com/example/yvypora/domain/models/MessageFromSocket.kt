package com.example.yvypora.domain.models

import java.util.Date

data class MessageFromSocket(
    val id: String,
    val fromName: String,
    val toName: String,

    val content: String,

    val toId: String,
    val FromId: String,
    val createdAt: String
)