package com.example.yvypora.models

import android.os.Parcel
import android.os.Parcelable

data class AddressCard(
    var titulo: String,
    var name_remetente: String,
    var telefone_remetente: String,
    var rua: String,
    var numero: Int,
    var cidade: String,
    var estado: String,
    var pais: String,
    var endereço_principal: Boolean,
) :Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo)
        parcel.writeString(name_remetente)
        parcel.writeString(telefone_remetente)
        parcel.writeString(rua)
        parcel.writeInt(numero)
        parcel.writeString(cidade)
        parcel.writeString(estado)
        parcel.writeString(pais)
        parcel.writeByte(if (endereço_principal) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddressCard> {
        override fun createFromParcel(parcel: Parcel): AddressCard {
            return AddressCard(parcel)
        }

        override fun newArray(size: Int): Array<AddressCard?> {
            return arrayOfNulls(size)
        }
    }
}
