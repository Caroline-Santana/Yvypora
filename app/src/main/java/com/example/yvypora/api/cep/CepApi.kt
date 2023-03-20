package com.example.yvypora.api.cep

import android.util.Log
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.models.Cep
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getCep(cep: String, onComplete: (Cep) -> Unit ) {
    val call = RetrofitApi.cepRetrofitService().getCep(cep)

    call.enqueue(object: Callback<Cep> {
        override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
            val res = response.body()
            Log.i("teste", res.toString())
            return onComplete.invoke(res!!)
        }

        override fun onFailure(call: Call<Cep>, t: Throwable) {
        }
    })
}