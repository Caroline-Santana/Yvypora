package com.example.yvypora.api

import com.example.yvypora.api.cep.CepApiService
import com.example.yvypora.api.commons.CommonsApiService
import com.example.yvypora.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    companion object {
        private lateinit var instance: Retrofit

        private fun getRetrofit(URL: String): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun commonsRetrofitService(): CommonsApiService {
            return getRetrofit(Constants.BASE_URL).create(CommonsApiService::class.java)
        }

        fun cepRetrofitService(): CepApiService {
            return getRetrofit(Constants.CEP_URL).create(CepApiService::class.java)
        }
    }
}