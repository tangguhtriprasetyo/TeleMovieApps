package com.example.dicodingmovieapps.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.dicodingmovieapps.data.source.local.entity.CastMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.DetailMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesWithDetail

@Dao
interface MoviesDao {

    @Query("SELECT * FROM moviesEntities")
    fun getMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM moviesEntities where favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM moviesEntities where movieId = :movieId")
    fun getMovieWithDetailById(movieId: Int): LiveData<MoviesWithDetail>

    @Query("SELECT * FROM castMoviesEntities where castMovieId = :movieId")
    fun getCastMovie(movieId: Int): LiveData<CastMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovie(movies: DetailMoviesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCastMovie(movies: CastMoviesEntity)

    @Update
    fun updateMovies(movies: MoviesEntity)

}