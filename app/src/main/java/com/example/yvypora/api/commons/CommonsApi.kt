package com.example.yvypora.api.commons

import com.example.yvypora.api.RetrofitApi
import com.example.yvypora.models.Credentials
import com.example.yvypora.models.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun auth(credentials: Credentials, onComplete: (Token) -> Unit) {
    val call =  RetrofitApi.commonsRetrofitService().auth(credentials)

    call.enqueue(object: Callback<Token> {
        override fun onResponse(call: Call<Token>, response: Response<Token>) {
            val res = response.body()


            if(res?.error == true) onComplete.invoke(Token())

            onComplete.invoke(res!!)
        }

        override fun onFailure(call: Call<Token>, t: Throwable) {

        }
    })
}

