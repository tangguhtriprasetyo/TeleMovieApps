package com.example.dicodingmovieapps.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "detailTvEntities",
    primaryKeys = ["detailTvId", "tvId"],
    foreignKeys = [ForeignKey(
        entity = TvEntity::class,
        parentColumns = ["tvId"],
        childColumns = ["tvId"]
    )]
)
data class DetailTvEntity(
    @NonNull
    @ColumnInfo(name = "detailTvId")
    var detailTvId: Int,

    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: Int,

    @ColumnInfo(name = "name")
    var name: String? = null,

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