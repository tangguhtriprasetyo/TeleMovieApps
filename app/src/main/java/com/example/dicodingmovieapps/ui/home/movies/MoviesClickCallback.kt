package com.example.dicodingmovieapps.ui.home.movies

import com.example.dicodingmovieapps.data.ListMoviesEntity

interface MoviesClickCallback {
    fun onItemClicked(movies: ListMoviesEntity)
}