package com.example.yvypora.api.commons

import android.util.Log
import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.models.Costumer
import com.example.yvypora.models.Credentials
import com.example.yvypora.models.RegisterCostumerResponse
import com.example.yvypora.models.Token
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun auth(credentials: Credentials, onComplete: (Token) -> Unit) {
    val call =  RetrofitApi.commonsRetrofitService().auth(credentials)

    call.enqueue(object: Callback<Token> {
        override fun onResponse(call: Call<Token>, response: Response<Token>) {
            val res = response.body()

            if (res?.error == true) onComplete.invoke(Token())

            try {
                onComplete.invoke(res!!)
            } catch (error: NullPointerException) {
                onComplete.invoke(Token())
            }
        }

        override fun onFailure(call: Call<Token>, t: Throwable) {

        }
    })
}


fun createCostumer(costumer: Costumer, onComplete: (RegisterCostumerResponse) -> Unit) {
    val call = RetrofitApi.commonsRetrofitService().createCostumer(costumer)
    call.enqueue(object: Callback<RegisterCostumerResponse> {
        override fun onResponse(
            call: Call<RegisterCostumerResponse>,
            response: Response<RegisterCostumerResponse>
        ) {
            val res = response.body()

            if (res?.error == true) {
                return onComplete.invoke(RegisterCostumerResponse(error = true))
            }

            return onComplete.invoke(RegisterCostumerResponse(error = false))

        }

        override fun onFailure(call: Call<RegisterCostumerResponse>, t: Throwable) {

        }

    })
}

