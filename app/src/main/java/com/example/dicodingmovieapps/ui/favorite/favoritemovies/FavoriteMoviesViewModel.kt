package com.example.dicodingmovieapps.ui.favorite.favoritemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.TvEntity

class FavoriteMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getFavoriteMovies(index: Int): LiveData<PagedList<MoviesEntity>>? {
        return when (index) {
            1 -> moviesRepository.getFavoriteMovies()
            else -> null
        }
    }

    fun getFavoriteTv(index: Int): LiveData<PagedList<TvEntity>>? {
        return when (index) {
            2 -> moviesRepository.getFavoriteTv()
            else -> null
        }
    }
}