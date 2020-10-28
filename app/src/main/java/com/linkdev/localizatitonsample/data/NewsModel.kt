package com.linkdev.localizatitonsample.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes

data class NewsModel(
    @IdRes var title: Int,
    @IdRes var content: Int,
    @DrawableRes var imgIDRes: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(title)
        parcel.writeInt(content)
        parcel.writeInt(imgIDRes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsModel> {
        override fun createFromParcel(parcel: Parcel): NewsModel {
            return NewsModel(parcel)
        }

        override fun newArray(size: Int): Array<NewsModel?> {
            return arrayOfNulls(size)
        }
    }


}

