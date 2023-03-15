package com.example.yvypora.service.Calling.user

import com.example.yvypora.service.constants.CONTENT_TYPE
import com.example.yvypora.service.model.LoginUsers
import retrofit2.Call
import retrofit2.http.*


const val contentType = CONTENT_TYPE

interface CallLogin {
    @GET("")
    fun getAll(): Call<List<LoginUsers>>

    @Headers("Content-Type:${CONTENT_TYPE}")
    @POST("user")
    fun save(@Body user: LoginUsers ): Call<String>

    @DELETE("/{id}")
    fun delete(@Path("id") id: Long): Call<String>
}
