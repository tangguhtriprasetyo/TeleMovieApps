package com.example.dicodingmovieapps.data.source.remote.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    private val retrofitInstance: Retrofit.Builder by lazy {
        Retrofit.Builder().apply {
            client(OkHttpClient.Builder().build())
            baseUrl("https://api.themoviedb.org/3/")
            addConverterFactory(GsonConverterFactory.create())
        }
    }

    val apiInstance: ApiService by lazy {
        retrofitInstance
            .build()
            .create(ApiService::class.java)
    }
}