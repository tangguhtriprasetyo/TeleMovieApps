package com.example.dicodingmovieapps.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.dicodingmovieapps.data.source.local.LocalDataSource
import com.example.dicodingmovieapps.data.source.local.entity.CastMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.DetailMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesWithDetail
import com.example.dicodingmovieapps.data.source.remote.ApiResponse
import com.example.dicodingmovieapps.data.source.remote.RemoteDataSource
import com.example.dicodingmovieapps.data.source.remote.response.DetailMovieResponse
import com.example.dicodingmovieapps.data.source.remote.response.MoviesCreditResponse
import com.example.dicodingmovieapps.data.source.remote.response.ResultsItem
import com.example.dicodingmovieapps.utils.AppExecutors
import com.example.dicodingmovieapps.vo.Resource

@Suppress("UNCHECKED_CAST")
class MoviesRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MoviesDataSource {

    companion object {
        @Volatile
        private var instance: MoviesRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MoviesRepository =
            instance ?: synchronized(this) {
                MoviesRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getListMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MoviesEntity>, List<ResultsItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return androidx.paging.LivePagedListBuilder(localDataSource.getAllMovies(), config)
                    .build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getMoviesList()

            override fun saveCallResult(data: List<ResultsItem>) {
                val moviesList = ArrayList<MoviesEntity>()
                for (response in data) {
                    val movie = MoviesEntity(
                        response.id!!,
                        title = response.title,
                        posterThumbnail = response.posterPath,
                        favorite = false
                    )
                    moviesList.add(movie)
                }
                localDataSource.insertMovies(moviesList)
            }
        }.asLiveData()
    }

    override fun getMovieWithDetail(movieId: Int): LiveData<Resource<MoviesWithDetail>> {
        return object : NetworkBoundResource<MoviesWithDetail, DetailMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MoviesWithDetail> =
                localDataSource.getMovieWithDetail(movieId)


            override fun shouldFetch(data: MoviesWithDetail?): Boolean =
                data?.mDetailMovie == null

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: DetailMovieResponse) {
                val score = data.voteAverage?.toInt() ?: 0
                val detailMovie = DetailMoviesEntity(
                    data.id!!,
                    data.id,
                    data.backdropPath,
                    data.releaseDate,
                    data.genres?.get(0)?.name,
                    score.times(10),
                    data.runtime.toString() + "m",
                    data.overview
                )
                localDataSource.insertDetailMovie(detailMovie)
            }
        }.asLiveData()
    }

    override fun getCastMovie(movieId: Int): LiveData<Resource<CastMoviesEntity>> {
        return object : NetworkBoundResource<CastMoviesEntity, MoviesCreditResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<CastMoviesEntity> =
                localDataSource.getCastMovie(movieId)


            override fun shouldFetch(data: CastMoviesEntity?): Boolean =
                data?.castMovieId == null || data.castMovieId == 0

            override fun createCall(): LiveData<ApiResponse<MoviesCreditResponse>> =
                remoteDataSource.getMovieCredits(movieId)

            override fun saveCallResult(data: MoviesCreditResponse) {
                val movieCredit = when (data.cast?.size) {
                    0 -> CastMoviesEntity(
                        null
                    )
                    1 -> {
                        CastMoviesEntity(
                            data.id,
                            data.cast[0]?.profilePath,
                            data.cast[0]?.name,
                            data.cast[0]?.character
                        )
                    }
                    2 -> {
                        CastMoviesEntity(
                            data.id,
                            data.cast[0]?.profilePath,
                            data.cast[0]?.name,
                            data.cast[0]?.character,
                            data.cast[1]?.profilePath,
                            data.cast[1]?.name,
                            data.cast[1]?.character
                        )
                    }
                    else -> {
                        CastMoviesEntity(
                            data.id,
                            data.cast?.get(0)?.profilePath,
                            data.cast?.get(0)?.name,
                            data.cast?.get(0)?.character,
                            data.cast?.get(1)?.profilePath,
                            data.cast?.get(1)?.name,
                            data.cast?.get(1)?.character,
                            data.cast?.get(2)?.profilePath,
                            data.cast?.get(2)?.name,
                            data.cast?.get(2)?.character,
                        )
                    }
                }
                localDataSource.insertCastMovie(movieCredit)
            }
        }.asLiveData()
    }

    override fun setFavoriteMovies(movie: MoviesEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(movie, state) }
    }

//    override fun getListTv(): LiveData<List<MoviesEntity>> {
//        val tvResults = MutableLiveData<List<MoviesEntity>>()
//        CoroutineScope(IO).launch {
//            remoteDataSource.getTvList(object : RemoteDataSource.LoadMoviesCallback {
//                override fun onAllMoviesReceived(moviesResponse: List<ResultsItem>) {
//                    val tvList = ArrayList<MoviesEntity>()
//                    for (response in moviesResponse) {
//                        val movies = MoviesEntity(
//                            id = response.id!!,
//                            title = response.title.toString(),
//                            posterThumbnail = response.posterPath.toString(),
//                            null,
//                            null,
//                            null,
//                            0,
//                            null,
//                            null
//                        )
//                        tvList.add(movies)
//                    }
//                    tvResults.postValue(tvList)
//                }
//            })
//        }
//        return tvResults
//    }
//
//    override fun getDetailTv(tvId: Int): LiveData<MoviesEntity> {
//        val detailTvResult = MutableLiveData<MoviesEntity>()
//        CoroutineScope(IO).launch {
//            remoteDataSource.getDetailTv(
//                tvId,
//                object : RemoteDataSource.LoadDetailTvCallback {
//                    override fun onDetailTvReceived(detailTvResponse: DetailTvResponse) {
//
//                        val score = detailTvResponse.voteAverage?.toInt() ?: 0
//                        val detailMovie = MoviesEntity(
//                            detailTvResponse.id,
//                            detailTvResponse.name,
//                            detailTvResponse.posterPath,
//                            detailTvResponse.backdropPath,
//                            detailTvResponse.firstAirDate,
//                            detailTvResponse.genres?.get(0)?.name.toString(),
//                            score.times(10),
//                            detailTvResponse.numberOfSeasons.toString() + " Season",
//                            detailTvResponse.overview
//                        )
//                        detailTvResult.postValue(detailMovie)
//                    }
//                })
//        }
//        return detailTvResult
//    }
//
//    override fun getTvCredits(tvId: Int): LiveData<CastMoviesEntity> {
//        val tvCreditResult = MutableLiveData<CastMoviesEntity>()
//        CoroutineScope(IO).launch {
//            remoteDataSource.getTvCredits(
//                tvId,
//                object : RemoteDataSource.LoadTvCredits {
//                    override fun onTvCreditReceived(tvCreditResponse: MoviesCreditResponse) {
//                        val totalCast = tvCreditResponse.cast?.size
//                        Log.d("TOTAL CAST", "$totalCast ")
//                        val tvCredit = when (totalCast) {
//                            0 -> CastMoviesEntity(
//                                null
//                            )
//                            1 -> {
//                                CastMoviesEntity(
//                                    tvCreditResponse.id,
//                                    tvCreditResponse.cast[0]?.profilePath,
//                                    tvCreditResponse.cast[0]?.name,
//                                    tvCreditResponse.cast[0]?.character
//                                )
//                            }
//                            2 -> {
//                                CastMoviesEntity(
//                                    tvCreditResponse.id,
//                                    tvCreditResponse.cast[0]?.profilePath,
//                                    tvCreditResponse.cast[0]?.name,
//                                    tvCreditResponse.cast[0]?.character,
//                                    tvCreditResponse.cast[1]?.profilePath,
//                                    tvCreditResponse.cast[1]?.name,
//                                    tvCreditResponse.cast[1]?.character
//                                )
//                            }
//                            else -> {
//                                CastMoviesEntity(
//                                    tvCreditResponse.id,
//                                    tvCreditResponse.cast?.get(0)?.profilePath,
//                                    tvCreditResponse.cast?.get(0)?.name,
//                                    tvCreditResponse.cast?.get(0)?.character,
//                                    tvCreditResponse.cast?.get(1)?.profilePath,
//                                    tvCreditResponse.cast?.get(1)?.name,
//                                    tvCreditResponse.cast?.get(1)?.character,
//                                    tvCreditResponse.cast?.get(2)?.profilePath,
//                                    tvCreditResponse.cast?.get(2)?.name,
//                                    tvCreditResponse.cast?.get(2)?.character,
//                                )
//                            }
//                        }
//                        tvCreditResult.postValue(tvCredit)
//                    }
//                })
//        }
//        return tvCreditResult
//    }
}