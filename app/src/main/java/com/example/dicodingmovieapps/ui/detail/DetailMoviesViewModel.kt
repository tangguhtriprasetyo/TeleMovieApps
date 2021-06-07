package com.example.dicodingmovieapps.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.local.entity.CastMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.CastTvEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesWithDetail
import com.example.dicodingmovieapps.data.source.local.entity.TvWithDetail
import com.example.dicodingmovieapps.vo.Resource

class DetailMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getDetailMovies(movieId: Int): LiveData<Resource<MoviesWithDetail>> {
        return moviesRepository.getMovieWithDetail(movieId)
    }

    fun getMovieCredit(movieId: Int): LiveData<Resource<CastMoviesEntity>> =
        moviesRepository.getCastMovie(movieId)

    fun getDetailTv(tvId: Int): LiveData<Resource<TvWithDetail>> {
        return moviesRepository.getTvWithDetail(tvId)
    }

    fun getTvCredit(tvId: Int): LiveData<Resource<CastTvEntity>> =
        moviesRepository.getCastTv(tvId)

}