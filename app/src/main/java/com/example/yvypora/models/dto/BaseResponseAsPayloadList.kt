package com.example.yvypora.models.dto

data class BaseResponseAsPayloadList<T>(
    val code: Int,
    val error: Boolean,
    val payload: List<T>
)
