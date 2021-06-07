package com.example.dicodingmovieapps.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dicodingmovieapps.data.source.local.entity.*

@Database(
    entities = [MoviesEntity::class, DetailMoviesEntity::class, CastMoviesEntity::class, TvEntity::class, DetailTvEntity::class, CastTvEntity::class],
    version = 1,
    exportSchema = false
)

abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    companion object {

        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "Movies.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}