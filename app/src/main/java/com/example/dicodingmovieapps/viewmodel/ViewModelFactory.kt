package com.example.dicodingmovieapps.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.di.Injection
import com.example.dicodingmovieapps.ui.detail.DetailMoviesViewModel
import com.example.dicodingmovieapps.ui.favorite.favoritemovies.FavoriteMoviesViewModel
import com.example.dicodingmovieapps.ui.home.movies.MoviesViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val mMoviesRepository: MoviesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java) -> {
                FavoriteMoviesViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(DetailMoviesViewModel::class.java) -> {
                DetailMoviesViewModel(mMoviesRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }
    }
}