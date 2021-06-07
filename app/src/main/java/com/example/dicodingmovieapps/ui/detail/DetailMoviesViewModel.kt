package com.example.dicodingmovieapps.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.local.entity.*
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

    fun setFavoriteMovie(moviesEntity: MoviesEntity) {
        val isFavorite = !moviesEntity.favorite
        moviesRepository.setFavoriteMovies(moviesEntity, isFavorite)
    }

    fun setFavoriteTv(tvEntity: TvEntity) {
        val isFavorite = !tvEntity.favorite
        moviesRepository.setFavoriteTv(tvEntity, isFavorite)
    }

}