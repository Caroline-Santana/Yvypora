package com.example.yvypora.domain.models

import com.google.gson.annotations.SerializedName


data class Order(
    @SerializedName("accepted")
    val accepted: Boolean,

    @SerializedName("order")
    val orderDetails: OrderDetails
)

data class OrderDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("accepted_status")
    val acceptedStatus: Boolean,
    @SerializedName("delivered_status_for_client")
    val deliveredStatusForClient: Boolean,
    @SerializedName("retreat_products_status")
    val retreatProductsStatus: Boolean,
    @SerializedName("deliverymanId")
    val deliverymanId: Int,
    @SerializedName("shopping_listId")
    val shoppingListId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("costumer_addressesId")
    val customerAddressesId: Int,
    @SerializedName("intent_payment_id")
    val intentPaymentId: String,
    @SerializedName("deliveryman")
    val deliveryman: _Deliveryman,
    @SerializedName("costumer_addresses")
    val customerAddresses: CustomerAddresses,
    @SerializedName("shopping_list")
    val shoppingList: ShoppingList,
    @SerializedName("products_in_shopping_list")
    val productsInShoppingList: List<ProductInShoppingList>
)

data class _Deliveryman(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password_hash")
    val passwordHash: String,
    @SerializedName("picture_uri")
    val pictureUri: String,
    @SerializedName("locationId")
    val locationId: Int,
    @SerializedName("online")
    val online: Boolean,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("genderId")
    val genderId: Int,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("gender")
    val gender: Gender
)

data class CustomerAddresses(
    @SerializedName("id")
    val id: Int,
    @SerializedName("addressId")
    val addressId: Int,
    @SerializedName("costumerId")
    val customerId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("address")
    val address: _Address
)

data class _Address(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cep")
    val cep: String,
    @SerializedName("logradouro")
    val logradouro: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("address_typeId")
    val addressTypeId: Int,
    @SerializedName("complemento")
    val complemento: String,
    @SerializedName("cityId")
    val cityId: Int,
    @SerializedName("uFId")
    val uFId: Int,
    @SerializedName("neighborhoodId")
    val neighborhoodId: Int,
    @SerializedName("locationId")
    val locationId: Int,
    @SerializedName("location")
    val location: Location
)

data class ShoppingList(
    @SerializedName("id")
    val id: Int,
    @SerializedName("freight")
    val freight: Double,
    @SerializedName("total")
    val total: Int,
    @SerializedName("costumerId")
    val customerId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("costumer")
    val customer: Customer,
    @SerializedName("products_in_shopping_list")
    val productsInShoppingList: List<ProductInShoppingList>
)

data class Customer(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password_hash")
    val passwordHash: String,
    @SerializedName("picture_uri")
    val pictureUri: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("genderId")
    val genderId: Int,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("cpf")
    val cpf: String?,
    @SerializedName("gender")
    val gender: Gender
)

data class ProductInShoppingList(
    @SerializedName("id")
    val id: Int,
    @SerializedName("shopping_listId")
    val shoppingListId: Int,
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("product")
    val product: _Product
)

data class _Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity")
    val quantity: Int?,
    @SerializedName("review")
    val review: Int,
    @SerializedName("active_for_selling")
    val activeForSelling: Boolean,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("marketerId")
    val marketerId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("category_of_productId")
    val categoryOfProductId: Int,
    @SerializedName("type_of_productId")
    val typeOfProductId: Int,
    @SerializedName("image_of_product")
    val imageOfProduct: List<ProductImage>,
    @SerializedName("marketer")
    val marketer: Marketer
)

data class ProductImage(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageId")
    val imageId: Int,
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("image")
    val image: Image
)

data class Image(
    @SerializedName("id")
    val id: Int,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("fairId")
    val fairId: Int?
)

data class Marketer(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password_hash")
    val passwordHash: String,
    @SerializedName("picture_uri")
    val pictureUri: String,
    @SerializedName("review")
    val review: Int,
    @SerializedName("online")
    val online: Boolean,
    @SerializedName("locationId")
    val locationId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("genderId")
    val genderId: Int,
    @SerializedName("cnpj")
    val cnpj: String?,
    @SerializedName("cpf")
    val cpf: String?,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("tent_name")
    val tentName: String,
    @SerializedName("location")
    val location: Location
)

data class Location(
    @SerializedName("id")
    val id: Int,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class Gender(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)
