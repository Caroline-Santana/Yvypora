package com.example.yvypora.service

import com.example.yvypora.service.constants.commons_api_base_url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactoryLogin {

    val retrofit = Retrofit.Builder().baseUrl(commons_api_base_url).addConverterFactory(
        GsonConverterFactory.create()).build()

    fun getApi(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}