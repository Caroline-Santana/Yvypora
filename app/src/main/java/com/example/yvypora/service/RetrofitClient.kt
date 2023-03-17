package com.example.yvypora.service

import com.example.yvypora.service.constants.API
import com.example.yvypora.service.constants.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    val retrofitClient = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val api = retrofitClient.create(API::class.java)
}