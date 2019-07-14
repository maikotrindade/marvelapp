package io.github.maikotrindade.marvelapp.characters.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CharactersResponse (
	@SerializedName("data") val data : CharacterData
)

data class CharacterData (
	@SerializedName("offset") val offset : String,
	@SerializedName("limit") val limit : String,
	@SerializedName("total") val total : String,
	@SerializedName("count") val count : String,
	@SerializedName("results") val results : List<Character>
)

data class Character (
	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String,
	@SerializedName("description") val description : String,
	@SerializedName("thumbnail") val thumbnail : Thumbnail
) : Parcelable {
	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Character> = object : Parcelable.Creator<Character> {
			override fun createFromParcel(source: Parcel): Character = Character(source)
			override fun newArray(size: Int): Array<Character?> = arrayOfNulls(size)
		}
	}

	constructor(source: Parcel): this(
		source.readString(),
		source.readString(),
		source.readString(),
		source.readParcelable<Thumbnail>(Thumbnail::class.java.classLoader)
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeString(id)
		writeString(name)
		writeString(description)
		writeParcelable(thumbnail, 0)
	}
}