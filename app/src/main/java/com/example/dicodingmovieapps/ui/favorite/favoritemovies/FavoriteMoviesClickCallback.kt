package com.example.dicodingmovieapps.ui.favorite.favoritemovies

import com.example.dicodingmovieapps.data.ListMoviesEntity

interface FavoriteMoviesClickCallback {
    fun onItemClicked(movies: ListMoviesEntity)
}