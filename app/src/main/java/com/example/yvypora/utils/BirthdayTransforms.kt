package com.example.yvypora.utils

fun formatBirthday(birthday: String): String {
    val year = birthday.takeLast(4)
    val month = (birthday[2].toString() + birthday[3].toString()).toString()
    val day = (birthday[0].toString() + birthday[1].toString()).toString()

    return "$year-$month-$day"
}