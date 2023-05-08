package com.example.yvypora.models.product

data class BaseResponse<T> (
    var code: Int? = null,
    var data: List<T>? = null,
    var error: Boolean? = null,
)
