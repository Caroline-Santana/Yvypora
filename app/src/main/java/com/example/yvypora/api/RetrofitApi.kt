package com.example.yvypora.api

import com.example.yvypora.api.cep.CepApiService
import com.example.yvypora.api.commons._CommonsAPIService
import com.example.yvypora.api.fairs.FairAPIService
import com.example.yvypora.api.fairs.FairsAPIService
import com.example.yvypora.api.product.ProductAPIService
import com.example.yvypora.api.purchases.PurchaseAPIService
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

        fun commonsRetrofitService(): _CommonsAPIService {
            return getRetrofit(Constants.BASE_URL).create(_CommonsAPIService::class.java)
        }

        fun productRetrofitService(): ProductAPIService {
            return getRetrofit(Constants.BASE_URL).create(ProductAPIService::class.java)
        }
        fun fairsRetrofitService(): FairAPIService {
            return getRetrofit(Constants.BASE_URL).create(FairAPIService::class.java)
        }

        fun purchaseRetrofitService(): PurchaseAPIService {
            return getRetrofit(Constants.BASE_URL).create(PurchaseAPIService::class.java)
        }

        fun cepRetrofitService(): CepApiService {
            return getRetrofit(Constants.CEP_URL).create(CepApiService::class.java)
        }

    }
}