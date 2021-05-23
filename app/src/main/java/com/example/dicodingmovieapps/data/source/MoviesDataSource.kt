package com.example.dicodingmovieapps.data.source

import androidx.lifecycle.LiveData
import com.example.dicodingmovieapps.data.CastMoviesEntity
import com.example.dicodingmovieapps.data.ListMoviesEntity
import com.example.dicodingmovieapps.data.MoviesEntity

interface MoviesDataSource {

    fun getListMovies(): LiveData<List<ListMoviesEntity>>

    fun getListTv(): LiveData<List<ListMoviesEntity>>

    fun getDetailMovie(movieId: Int): LiveData<MoviesEntity>

    fun getDetailTv(tvId: Int): LiveData<MoviesEntity>

    fun getMovieCredits(movieId: Int): LiveData<CastMoviesEntity>

    fun getTvCredits(tvId: Int): LiveData<CastMoviesEntity>
}