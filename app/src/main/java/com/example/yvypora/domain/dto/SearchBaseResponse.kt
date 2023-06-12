package com.example.yvypora.domain.dto

data class SearchBaseResponse<F, M, P>(
    var products: List<P?>? = null
)
