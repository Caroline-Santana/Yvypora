package com.example.yvypora.domain.dto

data class BaseResponseAsPayloadList<T>(
    val code: Int,
    val error: Boolean,
    val payload: List<T>
)
