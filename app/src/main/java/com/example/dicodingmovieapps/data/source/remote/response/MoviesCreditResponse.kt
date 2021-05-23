package com.example.dicodingmovieapps.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesCreditResponse(

    @field:SerializedName("cast")
    val cast: List<CastItem?>? = null,

    @field:SerializedName("id")
    val id: Int? = null
)

data class CastItem(

    @field:SerializedName("cast_id")
    val castId: Int? = null,

    @field:SerializedName("character")
    val character: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("profile_path")
    val profilePath: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)
