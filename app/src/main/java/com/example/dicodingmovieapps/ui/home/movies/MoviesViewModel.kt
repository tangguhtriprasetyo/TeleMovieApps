package com.example.dicodingmovieapps.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.TvEntity
import com.example.dicodingmovieapps.vo.Resource

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getDataMovies(index: Int, query: String): LiveData<Resource<PagedList<MoviesEntity>>>? {
        return when (index) {
            1 -> moviesRepository.getListMovies(query)
            else -> null
        }
    }

    fun getDataTv(index: Int, query: String): LiveData<Resource<PagedList<TvEntity>>>? {
        return when (index) {
            2 -> moviesRepository.getListTv(query)
            else -> null
        }
    }
}
