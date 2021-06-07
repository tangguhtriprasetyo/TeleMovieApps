package com.example.dicodingmovieapps.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.dicodingmovieapps.data.source.local.entity.*
import com.example.dicodingmovieapps.data.source.local.room.MoviesDao
import com.example.dicodingmovieapps.utils.SortUtils

class LocalDataSource private constructor(private val mMoviesDao: MoviesDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(moviesDao: MoviesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(moviesDao)
    }

    fun getAllMovies(query: String): DataSource.Factory<Int, MoviesEntity> {
        val sortedQuery = SortUtils.getSortedQuery(query)
        return mMoviesDao.getMovies(sortedQuery)
    }

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

    //TV
    fun getAllTv(query: String): DataSource.Factory<Int, TvEntity> {
        val sortedQuery = SortUtils.getSortedQueryTv(query)
        return mMoviesDao.getTv(sortedQuery)
    }

    fun getFavoriteTv(): DataSource.Factory<Int, TvEntity> = mMoviesDao.getFavoriteTv()

    fun getTvWithDetail(tvId: Int): LiveData<TvWithDetail> =
        mMoviesDao.getTvWithDetailById(tvId)

    fun getCastTv(tvId: Int): LiveData<CastTvEntity> = mMoviesDao.getCastTv(tvId)

    fun insertTv(tv: List<TvEntity>) = mMoviesDao.insertTv(tv)

    fun insertDetailTv(tv: DetailTvEntity) = mMoviesDao.insertDetailTv(tv)

    fun insertCastTv(tv: CastTvEntity) = mMoviesDao.insertCastTv(tv)

    fun setFavoriteTv(tv: TvEntity, newState: Boolean) {
        tv.favorite = newState
        mMoviesDao.updateTv(tv)
    }
}