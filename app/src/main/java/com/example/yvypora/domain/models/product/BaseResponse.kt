package com.example.yvypora.domain.models.product

data class BaseResponse<T> (
    var code: Int? = null,
    var data: List<T>? = null,
    var error: Boolean? = null,
)
