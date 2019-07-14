package io.github.maikotrindade.marvelapp.characters.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Thumbnail (

    @SerializedName("path") val path : String,
    @SerializedName("extension") val extension : String
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Thumbnail> = object : Parcelable.Creator<Thumbnail> {
            override fun createFromParcel(source: Parcel): Thumbnail = Thumbnail(source)
            override fun newArray(size: Int): Array<Thumbnail?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel): this(
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(path)
        writeString(extension)
    }
}