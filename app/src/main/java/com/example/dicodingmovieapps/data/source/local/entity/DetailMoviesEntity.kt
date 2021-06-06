package com.example.dicodingmovieapps.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "detailMovieEntities",
    primaryKeys = ["detailMovieId", "movieId"],
    foreignKeys = [ForeignKey(
        entity = MoviesEntity::class,
        parentColumns = ["movieId"],
        childColumns = ["movieId"]
    )]
)
data class DetailMoviesEntity(
    @NonNull
    @ColumnInfo(name = "detailMovieId")
    var detailMovieId: Int,

    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "posterHeader")
    var posterHeader: String? = null,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = null,

    @ColumnInfo(name = "genre")
    var genre: String? = null,

    @ColumnInfo(name = "userScore")
    var userScore: Int,

    @ColumnInfo(name = "duration")
    var duration: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null
)