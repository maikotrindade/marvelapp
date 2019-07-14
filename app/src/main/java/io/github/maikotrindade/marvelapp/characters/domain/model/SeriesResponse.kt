package io.github.maikotrindade.marvelapp.characters.domain.model

import com.google.gson.annotations.SerializedName

data class SeriesResponse (
    @SerializedName("data") val data : SeriesData
)

data class SeriesData (
    @SerializedName("offset") val offset : Int,
    @SerializedName("limit") val limit : Int,
    @SerializedName("total") val total : Int,
    @SerializedName("count") val count : Int,
    @SerializedName("results") val results : List<Series>
)

data class Series (

    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("thumbnail") val thumbnail : Thumbnail

)