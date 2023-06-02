package com.example.yvypora.domain.models

import com.example.yvypora.domain.models.dto.AddressType


data class AddressToRegister(
    var addressTypeId: Int? = null,
    var id: Int? = null,
    var cep: String? = null,
    var logradouro: String? = null,
    var number: Int? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
    var address_typeId: Int? = null,
    var complemento: String? = null,
    var cityId: Int? = null,
    var uFId: Int? = null,
    var neighborhoodId: Int? = null,
    var locationId: Int? = null,
    var city: String? = null,
    var type: AddressType? = null,
    var uf: String? = null,
    val neighborhood: String? = null,
)



data class Address(
    var addressTypeId: Int? = null,
    var id: Int? = null,
    var cep: String? = null,
    var logradouro: String? = null,
    var number: Int? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
    var default: Boolean,
    var address_typeId: Int? = null,
    var complemento: String? = null,
    var cityId: Int? = null,
    var uFId: Int? = null,
    var neighborhoodId: Int? = null,
    var locationId: Int? = null,
    var city: City? = null,
    var type: AddressType? = null,
    var uf: UF? = null,
    var neighborhood: Neighborhood? = null,
)


data class City(
    val name: String,
    val id: Int,
)


data class UF(
    val id: Int,
    val name: String
)

data class Neighborhood(
    val id: Int,
    val name: String
)

