package com.example.dicodingmovieapps.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicodingmovieapps.data.source.remote.api.ApiConfig
import com.example.dicodingmovieapps.data.source.remote.response.DetailMovieResponse
import com.example.dicodingmovieapps.data.source.remote.response.DetailTvResponse
import com.example.dicodingmovieapps.data.source.remote.response.MoviesCreditResponse
import com.example.dicodingmovieapps.data.source.remote.response.ResultsItem
import com.example.dicodingmovieapps.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

@Suppress("UNCHECKED_CAST")
class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }
    }

    fun getMoviesList(): LiveData<ApiResponse<List<ResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        CoroutineScope(IO).launch {
            try {
                val movies = ApiConfig.apiInstance.getMoviesList().await()
                resultMovies.postValue(ApiResponse.success(movies.results as List<ResultsItem>))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovies.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovies
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        CoroutineScope(IO).launch {
            try {
                val detailMovie = ApiConfig.apiInstance.getDetailMovie(movieId).await()
                resultDetailMovie.postValue(ApiResponse.success(detailMovie))
            } catch (e: IOException) {
                e.printStackTrace()
                resultDetailMovie.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        DetailMovieResponse()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultDetailMovie
    }

    fun getMovieCredits(movieId: Int): LiveData<ApiResponse<MoviesCreditResponse>> {
        EspressoIdlingResource.increment()
        val resultMovieCredits = MutableLiveData<ApiResponse<MoviesCreditResponse>>()
        CoroutineScope(IO).launch {
            try {
                val movieCredits = ApiConfig.apiInstance.getCreditsMovie(movieId).await()
                resultMovieCredits.postValue(ApiResponse.success(movieCredits))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovieCredits.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        MoviesCreditResponse()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovieCredits
    }

    //TV Data

    suspend fun getTvList(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.apiInstance.getTvList().await().results?.let { listMovies ->
            callback.onAllMoviesReceived(listMovies as List<ResultsItem>)
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

    suspend fun getTvCredits(tvId: Int, callback: LoadTvCredits) {
        EspressoIdlingResource.increment()
        ApiConfig.apiInstance.getCreditsTv(tvId).await().let { tvCredit ->
            callback.onTvCreditReceived(tvCredit)
            EspressoIdlingResource.decrement()
        }

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
