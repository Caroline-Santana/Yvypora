package com.example.yvypora.api.cep


import com.example.yvypora.models.Cep
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepApiService {
    @GET("{cep}/json/")
    fun getCep(@Path("cep") cep: String): Call<Cep>

}