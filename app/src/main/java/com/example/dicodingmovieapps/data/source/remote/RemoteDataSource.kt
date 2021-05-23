package com.example.dicodingmovieapps.data.source.remote

import com.example.dicodingmovieapps.data.source.remote.api.ApiConfig
import com.example.dicodingmovieapps.data.source.remote.response.DetailMovieResponse
import com.example.dicodingmovieapps.data.source.remote.response.DetailTvResponse
import com.example.dicodingmovieapps.data.source.remote.response.MoviesCreditResponse
import com.example.dicodingmovieapps.data.source.remote.response.ResultsItem
import com.example.dicodingmovieapps.utils.EspressoIdlingResource
import retrofit2.await

@Suppress("UNCHECKED_CAST")
class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }
    }

    suspend fun getMoviesList(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.apiInstance.getMoviesList().await().results?.let { listMovies ->
            callback.onAllMoviesReceived(listMovies as List<ResultsItem>)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvList(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.apiInstance.getTvList().await().results?.let { listMovies ->
            callback.onAllMoviesReceived(listMovies as List<ResultsItem>)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailMovie(movieId: Int, callback: LoadDetailMovieCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.apiInstance.getDetailMovie(movieId).await().let { detailMovie ->
            callback.onDetailMovieReceived(detailMovie)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailTv(movieId: Int, callback: LoadDetailTvCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.apiInstance.getDetailTv(movieId).await().let { detailMovie ->
            callback.onDetailTvReceived(detailMovie)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMovieCredits(movieId: Int, callback: LoadMovieCredits) {
        EspressoIdlingResource.increment()
        ApiConfig.apiInstance.getCreditsMovie(movieId).await().let { movieCredit ->
            callback.onMovieCreditReceived(movieCredit)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvCredits(tvId: Int, callback: LoadTvCredits) {
        EspressoIdlingResource.increment()
        ApiConfig.apiInstance.getCreditsTv(tvId).await().let { tvCredit ->
            callback.onTvCreditReceived(tvCredit)
            EspressoIdlingResource.decrement()
        }
    }


    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesResponse: List<ResultsItem>)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovieResponse: DetailMovieResponse)
    }

    interface LoadDetailTvCallback {
        fun onDetailTvReceived(detailTvResponse: DetailTvResponse)
    }

    interface LoadMovieCredits {
        fun onMovieCreditReceived(moviesCreditResponse: MoviesCreditResponse)
    }

    interface LoadTvCredits {
        fun onTvCreditReceived(tvCreditResponse: MoviesCreditResponse)
    }
}