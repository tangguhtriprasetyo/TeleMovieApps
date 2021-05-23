package com.example.dicodingmovieapps.data.source.remote.api

import com.example.dicodingmovieapps.data.source.remote.response.DetailMovieResponse
import com.example.dicodingmovieapps.data.source.remote.response.DetailTvResponse
import com.example.dicodingmovieapps.data.source.remote.response.MoviesCreditResponse
import com.example.dicodingmovieapps.data.source.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMoviesList(
        @Query("api_key") apiKey: String = "c2ada84d1a270f49f07cd1d263d5ce14"
    ): Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "c2ada84d1a270f49f07cd1d263d5ce14"
    ): Call<DetailMovieResponse>

    @GET("movie/{movie_id}/credits")
    fun getCreditsMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "c2ada84d1a270f49f07cd1d263d5ce14"
    ): Call<MoviesCreditResponse>

    @GET("tv/popular")
    fun getTvList(
        @Query("api_key") apiKey: String = "c2ada84d1a270f49f07cd1d263d5ce14"
    ): Call<MoviesResponse>

    @GET("tv/{tv_id}")
    fun getDetailTv(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = "c2ada84d1a270f49f07cd1d263d5ce14"
    ): Call<DetailTvResponse>

    @GET("tv/{tv_id}/credits")
    fun getCreditsTv(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = "c2ada84d1a270f49f07cd1d263d5ce14"
    ): Call<MoviesCreditResponse>
}