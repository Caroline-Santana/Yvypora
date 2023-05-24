package com.example.yvypora.domain.models.product

data class BaseResponseAsObject<T> (
    var code: Int? = null,
    var data: T? = null,
    var error: Boolean? = null,
)
