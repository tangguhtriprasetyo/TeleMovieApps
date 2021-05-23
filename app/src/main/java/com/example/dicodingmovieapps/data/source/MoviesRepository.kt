package com.example.dicodingmovieapps.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicodingmovieapps.data.CastMoviesEntity
import com.example.dicodingmovieapps.data.ListMoviesEntity
import com.example.dicodingmovieapps.data.MoviesEntity
import com.example.dicodingmovieapps.data.source.remote.RemoteDataSource
import com.example.dicodingmovieapps.data.source.remote.response.DetailMovieResponse
import com.example.dicodingmovieapps.data.source.remote.response.DetailTvResponse
import com.example.dicodingmovieapps.data.source.remote.response.MoviesCreditResponse
import com.example.dicodingmovieapps.data.source.remote.response.ResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class MoviesRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MoviesDataSource {

    companion object {
        @Volatile
        private var instance: MoviesRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MoviesRepository =
            instance ?: synchronized(this) {
                instance ?: MoviesRepository(remoteDataSource).apply { instance = this }
            }
    }

    override fun getListMovies(): LiveData<List<ListMoviesEntity>> {
        val moviesResults = MutableLiveData<List<ListMoviesEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMoviesList(object : RemoteDataSource.LoadMoviesCallback {
                override fun onAllMoviesReceived(moviesResponse: List<ResultsItem>) {
                    val moviesList = ArrayList<ListMoviesEntity>()
                    for (response in moviesResponse) {
                        val movies = ListMoviesEntity(
                            response.id!!,
                            response.title.toString(),
                            response.posterPath.toString(),
                        )
                        moviesList.add(movies)
                    }
                    moviesResults.postValue(moviesList)
                }
            })
        }
        return moviesResults
    }

    override fun getListTv(): LiveData<List<ListMoviesEntity>> {
        val tvResults = MutableLiveData<List<ListMoviesEntity>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvList(object : RemoteDataSource.LoadMoviesCallback {
                override fun onAllMoviesReceived(moviesResponse: List<ResultsItem>) {
                    val tvList = ArrayList<ListMoviesEntity>()
                    for (response in moviesResponse) {
                        val movies = ListMoviesEntity(
                            response.id!!,
                            response.title.toString(),
                            response.posterPath.toString(),
                        )
                        tvList.add(movies)
                    }
                    tvResults.postValue(tvList)
                }
            })
        }
        return tvResults
    }

    override fun getDetailMovie(movieId: Int): LiveData<MoviesEntity> {
        val detailMovieResult = MutableLiveData<MoviesEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailMovie(
                movieId,
                object : RemoteDataSource.LoadDetailMovieCallback {
                    override fun onDetailMovieReceived(detailMovieResponse: DetailMovieResponse) {

                        val score = detailMovieResponse.voteAverage?.toInt() ?: 0
                        val detailMovie = MoviesEntity(
                            detailMovieResponse.id,
                            detailMovieResponse.title,
                            detailMovieResponse.posterPath,
                            detailMovieResponse.backdropPath,
                            detailMovieResponse.releaseDate,
                            detailMovieResponse.genres?.get(0)?.name.toString(),
                            score.times(10),
                            detailMovieResponse.runtime.toString() + "m",
                            detailMovieResponse.overview
                        )
                        detailMovieResult.postValue(detailMovie)
                    }
                })
        }
        return detailMovieResult
    }

    override fun getDetailTv(tvId: Int): LiveData<MoviesEntity> {
        val detailTvResult = MutableLiveData<MoviesEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailTv(
                tvId,
                object : RemoteDataSource.LoadDetailTvCallback {
                    override fun onDetailTvReceived(detailTvResponse: DetailTvResponse) {

                        val score = detailTvResponse.voteAverage?.toInt() ?: 0
                        val detailMovie = MoviesEntity(
                            detailTvResponse.id,
                            detailTvResponse.name,
                            detailTvResponse.posterPath,
                            detailTvResponse.backdropPath,
                            detailTvResponse.firstAirDate,
                            detailTvResponse.genres?.get(0)?.name.toString(),
                            score.times(10),
                            detailTvResponse.numberOfSeasons.toString() + " Season",
                            detailTvResponse.overview
                        )
                        detailTvResult.postValue(detailMovie)
                    }
                })
        }
        return detailTvResult
    }

    override fun getMovieCredits(movieId: Int): LiveData<CastMoviesEntity> {
        val movieCreditResult = MutableLiveData<CastMoviesEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getMovieCredits(
                movieId,
                object : RemoteDataSource.LoadMovieCredits {
                    override fun onMovieCreditReceived(moviesCreditResponse: MoviesCreditResponse) {
                        val totalCast = moviesCreditResponse.cast?.size
                        Log.d("TOTAL CAST", "$totalCast ")
                        val movieCredit = when (totalCast) {
                            1 -> {
                                CastMoviesEntity(
                                    moviesCreditResponse.id,
                                    moviesCreditResponse.cast[0]?.profilePath,
                                    moviesCreditResponse.cast[0]?.name,
                                    moviesCreditResponse.cast[0]?.character
                                )
                            }
                            2 -> {
                                CastMoviesEntity(
                                    moviesCreditResponse.id,
                                    moviesCreditResponse.cast[0]?.profilePath,
                                    moviesCreditResponse.cast[0]?.name,
                                    moviesCreditResponse.cast[0]?.character,
                                    moviesCreditResponse.cast[1]?.profilePath,
                                    moviesCreditResponse.cast[1]?.name,
                                    moviesCreditResponse.cast[1]?.character
                                )
                            }
                            else -> {
                                CastMoviesEntity(
                                    moviesCreditResponse.id,
                                    moviesCreditResponse.cast?.get(0)?.profilePath,
                                    moviesCreditResponse.cast?.get(0)?.name,
                                    moviesCreditResponse.cast?.get(0)?.character,
                                    moviesCreditResponse.cast?.get(1)?.profilePath,
                                    moviesCreditResponse.cast?.get(1)?.name,
                                    moviesCreditResponse.cast?.get(1)?.character,
                                    moviesCreditResponse.cast?.get(2)?.profilePath,
                                    moviesCreditResponse.cast?.get(2)?.name,
                                    moviesCreditResponse.cast?.get(2)?.character,
                                )
                            }
                        }
                        movieCreditResult.postValue(movieCredit)
                    }
                })
        }
        return movieCreditResult
    }

    override fun getTvCredits(tvId: Int): LiveData<CastMoviesEntity> {
        val tvCreditResult = MutableLiveData<CastMoviesEntity>()
        CoroutineScope(IO).launch {
            remoteDataSource.getTvCredits(
                tvId,
                object : RemoteDataSource.LoadTvCredits {
                    override fun onTvCreditReceived(tvCreditResponse: MoviesCreditResponse) {
                        val totalCast = tvCreditResponse.cast?.size
                        Log.d("TOTAL CAST", "$totalCast ")
                        val tvCredit = when (totalCast) {
                            1 -> {
                                CastMoviesEntity(
                                    tvCreditResponse.id,
                                    tvCreditResponse.cast[0]?.profilePath,
                                    tvCreditResponse.cast[0]?.name,
                                    tvCreditResponse.cast[0]?.character
                                )
                            }
                            2 -> {
                                CastMoviesEntity(
                                    tvCreditResponse.id,
                                    tvCreditResponse.cast[0]?.profilePath,
                                    tvCreditResponse.cast[0]?.name,
                                    tvCreditResponse.cast[0]?.character,
                                    tvCreditResponse.cast[1]?.profilePath,
                                    tvCreditResponse.cast[1]?.name,
                                    tvCreditResponse.cast[1]?.character
                                )
                            }
                            else -> {
                                CastMoviesEntity(
                                    tvCreditResponse.id,
                                    tvCreditResponse.cast?.get(0)?.profilePath,
                                    tvCreditResponse.cast?.get(0)?.name,
                                    tvCreditResponse.cast?.get(0)?.character,
                                    tvCreditResponse.cast?.get(1)?.profilePath,
                                    tvCreditResponse.cast?.get(1)?.name,
                                    tvCreditResponse.cast?.get(1)?.character,
                                    tvCreditResponse.cast?.get(2)?.profilePath,
                                    tvCreditResponse.cast?.get(2)?.name,
                                    tvCreditResponse.cast?.get(2)?.character,
                                )
                            }
                        }
                        tvCreditResult.postValue(tvCredit)
                    }
                })
        }
        return tvCreditResult
    }
}