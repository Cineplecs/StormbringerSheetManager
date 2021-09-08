package com.example.stormbringersheetmanager.Utility

import android.os.Parcel
import android.os.Parcelable

class Weapon(
    var name: String = "", var requestFOR: Int? = 0, var requestDES: Int = 0, val damage: String? = "",
    val length: String? = "", val price: String? = "", val healthPoints: Int = 20
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    constructor() : this(
        "",
        0,
        0,
        "",
        "",
        "",
        0
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(requestFOR)
        parcel.writeInt(requestDES)
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

