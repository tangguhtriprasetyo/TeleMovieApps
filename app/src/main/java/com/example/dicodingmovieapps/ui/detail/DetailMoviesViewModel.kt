package com.example.dicodingmovieapps.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovieapps.data.MoviesEntity

class DetailMoviesViewModel : ViewModel() {

    private val detailMoviesData = MutableLiveData<MoviesEntity>()

    fun setDetailMovies(data: MoviesEntity) {
        detailMoviesData.value = data
    }

    fun getDetailMovies(): LiveData<MoviesEntity> {
        return detailMoviesData
    }
}