package com.example.dicodingmovieapps.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MoviesWithDetail(
    @Embedded
    var mMovies: MoviesEntity,
    @Relation(parentColumn = "movieId", entityColumn = "movieId")
    var mDetailMovie: DetailMoviesEntity? = null
)
