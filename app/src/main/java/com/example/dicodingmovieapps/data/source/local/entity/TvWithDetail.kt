package com.example.dicodingmovieapps.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TvWithDetail(
    @Embedded
    var mTv: TvEntity,
    @Relation(parentColumn = "tvId", entityColumn = "tvId")
    var mDetailTv: DetailTvEntity? = null
)