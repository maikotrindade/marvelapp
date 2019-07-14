package io.github.maikotrindade.marvelapp.characters.domain.model

import com.google.gson.annotations.SerializedName

data class ComicsResponse (
    @SerializedName("data") val data : ComicsData
)

data class ComicsData (
    @SerializedName("offset") val offset : Int,
    @SerializedName("limit") val limit : Int,
    @SerializedName("total") val total : Int,
    @SerializedName("count") val count : Int,
    @SerializedName("results") val results : List<Comic>
)

data class Comic (

    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("thumbnail") val thumbnail : Thumbnail

)