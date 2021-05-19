package com.example.dicodingmovieapps.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingmovieapps.data.MoviesEntity
import com.example.dicodingmovieapps.utils.DummyData

class MoviesViewModel : ViewModel() {

    private val moviesData = MutableLiveData<List<MoviesEntity>>()

    fun setData(index: Int) {
        if (index == 1) {
            moviesData.value = DummyData.generateDataMovies()
        } else if (index == 2) {
            moviesData.value = DummyData.generateDataTvSeries()
        }
    }

    fun getDataMovies(): LiveData<List<MoviesEntity>> {
        return moviesData
    }
}