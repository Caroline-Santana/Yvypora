package com.example.yvypora.service.Calling

import com.example.yvypora.service.constants.CONTENT_TYPE
import com.example.yvypora.service.model.LoginUsers
import com.example.yvypora.service.model.RetornoApi
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginCall {
    @Headers("Content-Type:${CONTENT_TYPE}")

    @POST("user/login")
    fun validate(@Body userLogin: LoginUsers): Call<RetornoApi>
}