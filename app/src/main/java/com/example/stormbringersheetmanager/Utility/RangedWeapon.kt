package com.example.stormbringersheetmanager.Utility

import android.os.Parcel
import android.os.Parcelable

class RangedWeapon(name : String, requestFOR : Int?, requestDES : Int?, damage : String?,
                   range : Int?, price : Int?) : Parcelable {
    var name = name
    var requestFOR = requestFOR
    var requestDES = requestDES
    var damage = damage
    var range = range
    var price = price
    var healthPoints = 20

    constructor(parcel: Parcel) : this(
        TODO("name"),
        TODO("requestFOR"),
        TODO("requestDES"),
        TODO("damage"),
        TODO("range"),
        TODO("price")
    ) {
        name = parcel.readString().toString()
        requestFOR = parcel.readValue(Int::class.java.classLoader) as? Int
        requestDES = parcel.readValue(Int::class.java.classLoader) as? Int
        damage = parcel.readString()
        range = parcel.readValue(Int::class.java.classLoader) as? Int
        price = parcel.readValue(Int::class.java.classLoader) as? Int
        healthPoints = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(requestFOR)
        parcel.writeValue(requestDES)
        parcel.writeString(damage)
        parcel.writeValue(range)
        parcel.writeValue(price)
        parcel.writeInt(healthPoints)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RangedWeapon> {
        override fun createFromParcel(parcel: Parcel): RangedWeapon {
            return RangedWeapon(parcel)
        }

        override fun newArray(size: Int): Array<RangedWeapon?> {
            return arrayOfNulls(size)
        }
    }
}