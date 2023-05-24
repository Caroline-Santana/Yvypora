package com.example.yvypora.api.cep

import android.util.Log
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.domain.models.Cep
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getCep(cep: String, onComplete: (com.example.yvypora.domain.models.Cep) -> Unit ) {
    val call = RetrofitApi.cepRetrofitService().getCep(cep)

    call.enqueue(object: Callback<com.example.yvypora.domain.models.Cep> {
        override fun onResponse(call: Call<com.example.yvypora.domain.models.Cep>, response: Response<com.example.yvypora.domain.models.Cep>) {
            val res = response.body()

            return onComplete.invoke(res!!)
        }

        override fun onFailure(call: Call<com.example.yvypora.domain.models.Cep>, t: Throwable) {
        }
    })
}

