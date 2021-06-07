package com.example.dicodingmovieapps.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.dicodingmovieapps.data.source.local.entity.*

@Dao
interface MoviesDao {

    @RawQuery(observedEntities = [MoviesEntity::class])
    fun getMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MoviesEntity>

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

    //TV Query
    @RawQuery(observedEntities = [TvEntity::class])
    fun getTv(query: SupportSQLiteQuery): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM tvEntities where favorite = 1")
    fun getFavoriteTv(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM tvEntities where tvId = :tvId")
    fun getTvWithDetailById(tvId: Int): LiveData<TvWithDetail>

    @Query("SELECT * FROM castTvEntities where castTvId = :tvId")
    fun getCastTv(tvId: Int): LiveData<CastTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: List<TvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTv(tv: DetailTvEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCastTv(tv: CastTvEntity)

    @Update
    fun updateTv(tv: TvEntity)

}