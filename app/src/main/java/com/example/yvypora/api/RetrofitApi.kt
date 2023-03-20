package com.example.yvypora.api

import com.example.yvypora.api.commons.CommonsApiService
import com.example.yvypora.service.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    companion object {
        private lateinit var instance: Retrofit

        fun getRetrofit(): Retrofit {
            if (::instance.isInitialized) {
                return instance
            }

            instance = Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return instance
        }

        fun commonsRetrofitService(): CommonsApiService {
            return getRetrofit().create(CommonsApiService::class.java)
        }
    }
}