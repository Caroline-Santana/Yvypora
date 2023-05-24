package com.example.yvypora.models

import java.util.Calendar

data class Message(
    var text:String?= null,
    var recipient_id: String,
    var time: Long = Calendar.getInstance().timeInMillis,
    var isOut: Boolean = false
)

val message_dummy = listOf(

    Message(
        text = "ai calica",
        recipient_id = "Ariana Grande",
        isOut = false
    ),
    Message(
        text = "Entt se arruma muie ;) ;) ;)",
        recipient_id = "Faustão",
        isOut = true
    ),
    Message(
        text = "queruuuu",
        recipient_id = "Ariana Grande",
        isOut = false
    ),
    Message(
        text = "date hj xuxu?",
        recipient_id = "Faustão",
        isOut = true
    ),
    Message(
        text = "Oi bebê...",
        recipient_id = "Ariana Grande",
        isOut = false
    ),
    Message(
        text = "Oi amor...",
        recipient_id = "Faustão",
        isOut = true
    )


)