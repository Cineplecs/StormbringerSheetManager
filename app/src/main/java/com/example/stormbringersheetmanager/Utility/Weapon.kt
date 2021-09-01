package com.example.stormbringersheetmanager.Utility

import android.os.Parcel
import android.os.Parcelable

class Weapon(
    name: String, requestFOR: Int?, requestDES: Int, damage: String?,
    length: String?, price: String?
) : Parcelable {
    var name: String = name
    var requestFOR: Int? = requestFOR
    var requestDES: Int = requestDES
    var damage: String? = damage
    var length: String? = length
    var price: String? = price
    var healthPoints = 20

    constructor(parcel: Parcel) : this(
        TODO("name"),
        TODO("requestFOR"),
        TODO("requestDES"),
        TODO("damage"),
        TODO("length"),
        TODO("price")
    ) {
        name = parcel.readString().toString()
        requestFOR = parcel.readValue(Int::class.java.classLoader) as? Int
        requestDES = (parcel.readValue(Int::class.java.classLoader) as? Int)!!
        damage = parcel.readString()
        length = parcel.readString()
        price = parcel.readString()
        healthPoints = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(requestFOR)
        parcel.writeValue(requestDES)
        parcel.writeString(damage)
        parcel.writeString(length)
        parcel.writeString(price)
        parcel.writeInt(healthPoints)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Weapon> {
        override fun createFromParcel(parcel: Parcel): Weapon {
            return Weapon(parcel)
        }

        override fun newArray(size: Int): Array<Weapon?> {
            return arrayOfNulls(size)
        }
    }
}

