package com.example.yvypora.api.commons

import com.example.yvypora.models.Costumer
import com.example.yvypora.models.Credentials
import com.example.yvypora.models.RegisterCostumerResponse
import com.example.yvypora.models.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CommonsApiService {
    @POST("login/")
    fun auth(@Body credentials: Credentials): Call<Token>

    @POST("register/costumer")
    fun createCostumer(@Body costumer: Costumer): Call<Any>


    @POST("register/marketer")
    fun createMarketer(@Body marketer: Marketer): Call<Any>
}