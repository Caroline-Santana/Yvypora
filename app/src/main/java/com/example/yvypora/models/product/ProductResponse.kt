package com.example.yvypora.models.product

import com.example.yvypora.models.dto.SaleOff
import com.example.yvypora.models.dto.TypeOfPrice
import com.example.yvypora.models.marketer.Marketer
import com.example.yvypora.models.product.ImageOfProduct
import com.google.gson.annotations.SerializedName


data class ProductResponse (
    val id: Int,
    val name: String,
    val price: Double,
    val discount: Double?,
    val quantity: Int?,
    val review: Int,
    val avaliations: Int,
    @SerializedName("active_for_selling")
    val activeForSelling: Boolean,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    val marketerId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val description: String,
    @SerializedName("category_of_productId")
    val categoryOfProductId: Int,
    @SerializedName("type_of_productId")
    val typeOfProductId: Int,
    @SerializedName("sale_off")
    val saleOff: List<SaleOff?>,
    @SerializedName("type_of_price")
    val typeOfPrice: TypeOfPrice,
    @SerializedName("image_of_product")
    val imageOfProduct: List<ImageOfProduct>,
    val marketer: Marketer,
    var latitude: Boolean? = null,
    var longitude: Boolean? = null,
)

