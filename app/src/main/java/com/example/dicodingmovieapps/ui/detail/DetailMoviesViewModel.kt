package com.example.dicodingmovieapps.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovieapps.data.CastMoviesEntity
import com.example.dicodingmovieapps.data.MoviesEntity
import com.example.dicodingmovieapps.data.source.MoviesRepository

class DetailMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getDetailMovies(movieId: Int): LiveData<MoviesEntity> =
        moviesRepository.getDetailMovie(movieId)

    fun getMovieCredit(movieId: Int): LiveData<CastMoviesEntity> =
        moviesRepository.getMovieCredits(movieId)

    fun getDetailTv(tvId: Int): LiveData<MoviesEntity> =
        moviesRepository.getDetailTv(tvId)

    fun getTvCredit(tvId: Int): LiveData<CastMoviesEntity> =
        moviesRepository.getTvCredits(tvId)
}