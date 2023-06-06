package com.example.yvypora.api.norelational

import com.example.yvypora.domain.models.*
import com.example.yvypora.domain.models.product.BaseResponse


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface NoRelationalAPIService {
    @GET("chat")
    fun listChat(
        @Query("receiverId") receiverId: Int,
        @Query("senderId") senderId: Int
    ) : Call<BaseResponse<MessageFromSocket>>
}