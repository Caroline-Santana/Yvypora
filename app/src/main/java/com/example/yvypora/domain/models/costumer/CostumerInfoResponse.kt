package com.example.yvypora.models

import com.example.yvypora.domain.models.costumer.CustomerAddress
import com.example.yvypora.domain.dto.Gender

data class CostumerInfoResponse(
    var gender: Gender? = null,
    var name: String? = null,
    var email: String? = null,
    var costumer_addresses: List<CustomerAddress>? = null
)




