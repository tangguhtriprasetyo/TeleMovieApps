package com.example.dicodingmovieapps.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "castMoviesEntities")
data class CastMoviesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "castMovieId")
    var castMovieId: Int? = null,

    @ColumnInfo(name = "imgCast1")
    var imgCast1: String? = null,

    @ColumnInfo(name = "artist1")
    var artist1: String? = null,

    @ColumnInfo(name = "casting1")
    var casting1: String? = null,

    @ColumnInfo(name = "imgCast2")
    var imgCast2: String? = null,

    @ColumnInfo(name = "artist2")
    var artist2: String? = null,

    @ColumnInfo(name = "casting2")
    var casting2: String? = null,

    @ColumnInfo(name = "imgCast3")
    var imgCast3: String? = null,

    @ColumnInfo(name = "artist3")
    var artist3: String? = null,

    @ColumnInfo(name = "casting3")
    var casting3: String? = null
)
