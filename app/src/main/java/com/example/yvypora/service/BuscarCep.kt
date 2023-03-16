package com.example.yvypora.service

import android.util.Log
import com.example.yvypora.service.model.Cep
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun buscarCep(cep: String, onComplete: (String) -> Unit) {

    var logradouro = ""


    val call = RetrofitFactoryCep()
        .retrofitService()
        .getCep(cep)

    call.enqueue(object: Callback<Cep> {

        override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
            logradouro = response.body()!!.logradouro ?: ""
            Log.i("xxx", logradouro)
            onComplete.invoke(logradouro)
        }

        override fun onFailure(call: Call<Cep>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
}