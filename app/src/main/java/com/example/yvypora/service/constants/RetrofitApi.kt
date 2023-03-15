package com.example.yvypora.service.constants

import android.provider.SyncStateContract.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    companion object {
        private lateinit var instance: Retrofit

        fun getRetrofit(): Retrofit {

            if (!::instance.isInitialized) {
                instance = Retrofit
                    .Builder()
                    .baseUrl(commons_api_base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return instance
        }
    }
}