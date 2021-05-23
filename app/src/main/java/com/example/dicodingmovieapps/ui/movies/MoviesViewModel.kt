package com.example.dicodingmovieapps.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovieapps.data.ListMoviesEntity
import com.example.dicodingmovieapps.data.source.MoviesRepository

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getDataMovies(index: Int): LiveData<List<ListMoviesEntity>>? {
        return when (index) {
            1 -> moviesRepository.getListMovies()
            2 -> moviesRepository.getListTv()
            else -> null
        }
    }
}
