package com.example.yvypora.service.model



data class UserClient(
    val response: String,
    val data:List<UserData>
){
    data class UserData(
        val id:String,
        val name:String,
        val email:String,
        val password:String,
        val cpf:String,
        val cep:String
    )
}
