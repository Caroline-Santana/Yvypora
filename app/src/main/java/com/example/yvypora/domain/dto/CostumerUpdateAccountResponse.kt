package com.example.yvypora.domain.dto

data class CostumerUpdateAccountResponse(
    val data: Details
)


data class Details (
    val newToken: String
)