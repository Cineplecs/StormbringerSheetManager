package com.example.stormbringersheetmanager.Utility

import android.os.Parcel
import android.os.Parcelable


class Skills(
    type: String, name: String, iniziale: Int, esperienza: Boolean
) : Parcelable {
    var type : String = type
    var name = name
    var iniziale = iniziale
    var esperienza = esperienza

    constructor(parcel: Parcel) : this(
        TODO("type"),
        TODO("name"),
        TODO("iniziale"),
        TODO("esperienza")
    ) {
        type = parcel.readString().toString()
        name = parcel.readString().toString()
        iniziale = parcel.readInt()
        esperienza = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(name)
        parcel.writeInt(iniziale)
        parcel.writeByte(if (esperienza) 1 else 0)
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
