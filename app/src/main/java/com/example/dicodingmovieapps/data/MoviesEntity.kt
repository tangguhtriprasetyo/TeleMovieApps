package com.example.dicodingmovieapps.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesEntity(
    var id: Int? = null,
    var title: String? = null,
    var posterThumbnail: String? = null,
    var posterHeader: String? = null,
    var releaseDate: String? = null,
    var genre: String? = null,
    var userScore: Int,
    var duration: String? = null,
    var overview: String? = null
) : Parcelable
