package com.example.dicodingmovieapps.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesEntity(
    var title: String,
    var posterThumbnail: String,
    var posterHeader: String,
    var releaseDate: String,
    var genre: String,
    var userScore: Int,
    var duration: String,
    var overview: String,
    var imgCast1: String,
    var artist1: String,
    var casting1: String,
    var imgCast2: String,
    var artist2: String,
    var casting2: String,
    var imgCast3: String,
    var artist3: String,
    var casting3: String
) : Parcelable
