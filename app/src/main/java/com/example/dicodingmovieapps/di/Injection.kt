package com.example.dicodingmovieapps.di

import android.content.Context
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.local.LocalDataSource
import com.example.dicodingmovieapps.data.source.local.room.MoviesDatabase
import com.example.dicodingmovieapps.data.source.remote.RemoteDataSource
import com.example.dicodingmovieapps.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MoviesRepository {

        val database = MoviesDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.moviesDao())
        val appExecutors = AppExecutors()

        return MoviesRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}