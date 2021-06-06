package com.example.dicodingmovieapps.ui.favorite.favoritemovies

import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity

interface FavoriteMoviesClickCallback {
    fun onItemClicked(movies: MoviesEntity)
}