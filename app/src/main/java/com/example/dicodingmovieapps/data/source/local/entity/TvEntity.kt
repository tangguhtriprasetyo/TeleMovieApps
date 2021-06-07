package com.example.dicodingmovieapps.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tvEntities")
data class TvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: Int,

    @ColumnInfo(name = "posterThumbnail")
    var posterThumbnail: String? = null,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
) : Parcelable