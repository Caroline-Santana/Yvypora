package com.example.yvypora.models

import com.example.yvypora.models.dto.AddressType

data class Address(
 var uf: String? = null,
 var city: String? = null,
 var neighborhood: String? = null,
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
 var type: AddressType? = null
)
