package com.example.dicodingmovieapps.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.dicodingmovieapps.data.source.local.entity.CastMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.DetailMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesWithDetail
import com.example.dicodingmovieapps.data.source.local.room.MoviesDao

class LocalDataSource private constructor(private val mMoviesDao: MoviesDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(moviesDao: MoviesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(moviesDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MoviesEntity> = mMoviesDao.getMovies()

    fun getFavoriteMovies(): DataSource.Factory<Int, MoviesEntity> = mMoviesDao.getFavoriteMovies()

    fun getMovieWithDetail(movieId: Int): LiveData<MoviesWithDetail> =
        mMoviesDao.getMovieWithDetailById(movieId)

    fun getCastMovie(movieId: Int): LiveData<CastMoviesEntity> = mMoviesDao.getCastMovie(movieId)

    fun insertMovies(movies: List<MoviesEntity>) = mMoviesDao.insertMovies(movies)

    fun insertDetailMovie(movies: DetailMoviesEntity) = mMoviesDao.insertDetailMovie(movies)

    fun insertCastMovie(movies: CastMoviesEntity) = mMoviesDao.insertCastMovie(movies)

    fun setFavoriteMovies(movies: MoviesEntity, newState: Boolean) {
        movies.favorite = newState
        mMoviesDao.updateMovies(movies)
    }
}