package com.example.yvypora.domain.models.dto

data class BaseResponseAsPayloadList<T>(
    val code: Int,
    val error: Boolean,
    val payload: List<T>
)
