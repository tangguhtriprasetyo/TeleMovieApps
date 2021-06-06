package com.example.dicodingmovieapps.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.local.entity.CastMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesWithDetail
import com.example.dicodingmovieapps.vo.Resource

class DetailMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    val moviesId = MutableLiveData<Int>()

    fun setSelectedMovies(movieId: Int) {
        this.moviesId.value = movieId
    }

    fun getDetailMovies(): LiveData<Resource<MoviesWithDetail>> =
        Transformations.switchMap(moviesId) { mMoviesId ->
            moviesRepository.getMovieWithDetail(mMoviesId)
        }

    fun getMovieCredit(movieId: Int): LiveData<Resource<CastMoviesEntity>> =
        moviesRepository.getCastMovie(movieId)
}