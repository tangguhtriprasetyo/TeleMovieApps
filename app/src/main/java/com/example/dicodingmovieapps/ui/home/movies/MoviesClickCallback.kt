package com.example.dicodingmovieapps.ui.home.movies

import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.TvEntity

interface MoviesClickCallback {
    fun onItemMovieClicked(movies: MoviesEntity)
    fun onItemTvClicked(tv: TvEntity)
}