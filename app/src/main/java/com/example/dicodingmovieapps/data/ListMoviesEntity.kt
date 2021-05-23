package com.example.dicodingmovieapps.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListMoviesEntity(
    var id: Int,
    var title: String,
    var posterPath: String
) : Parcelable
