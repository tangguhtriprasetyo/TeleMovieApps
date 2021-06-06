package com.example.dicodingmovieapps.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.vo.Resource

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getDataMovies(index: Int): LiveData<Resource<PagedList<MoviesEntity>>>? {
        return when (index) {
            1 -> moviesRepository.getListMovies()
            2 -> moviesRepository.getListMovies()
            else -> null
        }
    }
}
