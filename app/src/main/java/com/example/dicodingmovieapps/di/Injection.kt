package com.example.dicodingmovieapps.di

import android.content.Context
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): MoviesRepository {
        val remoteDataSource = RemoteDataSource.getInstance()

        return MoviesRepository.getInstance(remoteDataSource)
    }
}