package com.example.yvypora.models.product

data class BaseResponseAsObject<T> (
    var code: Int? = null,
    var data: T,
    var error: Boolean? = null,
)