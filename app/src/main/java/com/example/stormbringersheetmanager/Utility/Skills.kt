package com.example.stormbringersheetmanager.Utility

import android.os.Parcel
import android.os.Parcelable


class Skills(
    type: String? = "", name: String? = "", iniziale: Int? = 0, esperienza: Boolean? = true
) : Parcelable {
    var type : String? = type
    var name : String? = name
    var iniziale : Int? = iniziale
    var esperienza : Boolean? = esperienza

    constructor(parcel: Parcel) : this(
        TODO("type"),
        TODO("name"),
        TODO("iniziale"),
        TODO("esperienza")
    ) {
        type = parcel.readString()
        name = parcel.readString()
        iniziale = parcel.readValue(Int::class.java.classLoader) as? Int
        esperienza = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    constructor() : this(
        "","",0,true
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(name)
        parcel.writeValue(iniziale)
        parcel.writeValue(esperienza)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Skills> {
        override fun createFromParcel(parcel: Parcel): Skills {
            return Skills(parcel)
        }

        override fun newArray(size: Int): Array<Skills?> {
            return arrayOfNulls(size)
        }
    }

}
