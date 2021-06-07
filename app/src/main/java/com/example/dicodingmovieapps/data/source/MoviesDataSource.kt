package com.example.dicodingmovieapps.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.dicodingmovieapps.data.source.local.entity.*
import com.example.dicodingmovieapps.vo.Resource

interface MoviesDataSource {

    fun getListMovies(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getFavoriteMovies(): LiveData<PagedList<MoviesEntity>>

    fun getMovieWithDetail(movieId: Int): LiveData<Resource<MoviesWithDetail>>

    fun getCastMovie(movieId: Int): LiveData<Resource<CastMoviesEntity>>

    fun setFavoriteMovies(movie: MoviesEntity, state: Boolean)

    fun getListTv(): LiveData<Resource<PagedList<TvEntity>>>

    fun getFavoriteTv(): LiveData<PagedList<TvEntity>>

    fun getTvWithDetail(tvId: Int): LiveData<Resource<TvWithDetail>>

    fun getCastTv(tvId: Int): LiveData<Resource<CastTvEntity>>

    fun setFavoriteTv(tv: TvEntity, state: Boolean)
}