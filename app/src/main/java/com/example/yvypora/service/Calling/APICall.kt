package com.example.yvypora.service.Calling

import android.util.Log
import com.example.yvypora.service.Calling.user.CallUser
import com.example.yvypora.service.constants.RetrofitApi
import com.example.yvypora.service.model.LoginUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APICall {
//    companion object {
//        fun callPost(user: LoginUsers): List<String> {
//
//            var resultado = listOf<String>()
//
//            val retrofit = RetrofitApi.getRetrofit()
//            val userCall = retrofit.create(LoginCall::class.java)
////            val callInsertUser = CallUser.save(LoginUsers)
//
//
//            callInsertUser.enqueue(object :
//                Callback<String> {
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    resultado = listOf<String>(
//                        response.message()!!.toString(),
//                        response.code()!!.toString()
//                    )
//
//                    Log.i("respon post", response.code()!!.toString())
//                }
//
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    resultado = listOf<String>(
//                        t.message.toString()
//                    )
//
//                    Log.i("respon post", t.message.toString())
//                }
//            }
//            )
//
//            return resultado
//        }
//    }
}