package com.example.yvypora.domain.models

import com.example.yvypora.domain.models.costumer.CustomerAddress
import com.example.yvypora.domain.dto.Gender
import com.example.yvypora.domain.dto.Location
import com.example.yvypora.domain.dto.TypeOfUser
import com.google.gson.annotations.SerializedName

data class User (
    var id: Int? = null,
    var name: String?= null,
    var email: String?= null,
    var password_hash: String?= null,
    var picture_uri: String?= null,
    var review: Int?= null,
    var online: Boolean?= null,
    var locationId: Int?= null,
    var created_at: String?= null,
    var birthday: String?= null,
    var updated_at: String?= null,
    var genderId: Int?= null,
    var cnpj: String?= null,
    var cpf: String?= null,
    var phone: String?= null,
    var tent_name: String?= null,
    var gender: Gender?= null,
    var location: Location?= null,

    @SerializedName("costumer_addresses")
    var costumerAddresses: List<CustomerAddress>? = null,

    @SerializedName("typeof")
    var typeOf: TypeOfUser?= null
)