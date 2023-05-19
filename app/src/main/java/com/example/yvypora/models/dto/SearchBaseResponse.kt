package com.example.yvypora.models.dto

data class SearchBaseResponse<F, M, P>(
    var products: List<P?>? = null
)
