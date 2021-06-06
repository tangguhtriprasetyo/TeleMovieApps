package com.example.dicodingmovieapps.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.dicodingmovieapps.data.source.local.entity.CastMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesWithDetail
import com.example.dicodingmovieapps.vo.Resource

interface MoviesDataSource {

    fun getListMovies(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getMovieWithDetail(movieId: Int): LiveData<Resource<MoviesWithDetail>>

    fun getCastMovie(movieId: Int): LiveData<Resource<CastMoviesEntity>>

    fun setFavoriteMovies(movie: MoviesEntity, state: Boolean)
}