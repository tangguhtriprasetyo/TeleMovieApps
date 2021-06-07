package com.example.dicodingmovieapps.ui.favorite.favoritemovies

import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.TvEntity

interface FavoriteMoviesClickCallback {
    fun onItemMovieClicked(movies: MoviesEntity)
    fun onItemTvClicked(tv: TvEntity)
}