package com.example.yvypora.domain.models.dto

data class SearchBaseResponse<F, M, P>(
    var products: List<P?>? = null
)
