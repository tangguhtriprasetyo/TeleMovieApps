package com.example.dicodingmovieapps.data.source.remote.api

import com.example.dicodingmovieapps.BuildConfig
import com.example.dicodingmovieapps.data.source.remote.response.DetailMovieResponse
import com.example.dicodingmovieapps.data.source.remote.response.DetailTvResponse
import com.example.dicodingmovieapps.data.source.remote.response.MoviesCreditResponse
import com.example.dicodingmovieapps.data.source.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getMoviesList(
        @Query("api_key") apiKey: String = BuildConfig.API_AUTH
    ): Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_AUTH
    ): Call<DetailMovieResponse>

    @GET("movie/{movie_id}/credits")
    fun getCreditsMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_AUTH
    ): Call<MoviesCreditResponse>

    @GET("tv/on_the_air")
    fun getTvList(
        @Query("api_key") apiKey: String = BuildConfig.API_AUTH
    ): Call<MoviesResponse>

    @GET("tv/{tv_id}")
    fun getDetailTv(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_AUTH
    ): Call<DetailTvResponse>

    @GET("tv/{tv_id}/credits")
    fun getCreditsTv(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_AUTH
    ): Call<MoviesCreditResponse>
}