package com.example.dicodingmovieapps.ui.home.movies

import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity


interface MoviesClickCallback {
    fun onItemClicked(movies: MoviesEntity)
}