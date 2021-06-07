package com.example.dicodingmovieapps.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.dicodingmovieapps.data.source.local.LocalDataSource
import com.example.dicodingmovieapps.data.source.local.entity.*
import com.example.dicodingmovieapps.data.source.remote.ApiResponse
import com.example.dicodingmovieapps.data.source.remote.RemoteDataSource
import com.example.dicodingmovieapps.data.source.remote.response.DetailMovieResponse
import com.example.dicodingmovieapps.data.source.remote.response.DetailTvResponse
import com.example.dicodingmovieapps.data.source.remote.response.MoviesCreditResponse
import com.example.dicodingmovieapps.data.source.remote.response.ResultsItem
import com.example.dicodingmovieapps.utils.AppExecutors
import com.example.dicodingmovieapps.vo.Resource

@Suppress("UNCHECKED_CAST")
class FakeMoviesRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MoviesDataSource {

    override fun getListMovies(query: String): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MoviesEntity>, List<ResultsItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAllMovies(query),
                    config
                )
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
                        posterThumbnail = response.posterPath,
                        favorite = false,
                        response.voteAverage?.toInt() ?: 0
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
                    data.id!!,
                    data.title,
                    data.backdropPath,
                    data.releaseDate,
                    data.genres?.get(0)?.name,
                    score.times(10),
                    data.runtime.toString() + " m",
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
                            data.cast?.get(0)?.profilePath,
                            data.cast?.get(0)?.name,
                            data.cast?.get(0)?.character
                        )
                    }
                    2 -> {
                        CastMoviesEntity(
                            data.id,
                            data.cast?.get(0)?.profilePath,
                            data.cast?.get(0)?.name,
                            data.cast?.get(0)?.character,
                            data.cast?.get(1)?.profilePath,
                            data.cast?.get(1)?.name,
                            data.cast?.get(1)?.character
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

    override fun getFavoriteMovies(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovies(movie: MoviesEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(movie, state) }
    }

    override fun getListTv(query: String): LiveData<Resource<PagedList<TvEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvEntity>, List<ResultsItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTv(query), config)
                    .build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getTvList()

            override fun saveCallResult(data: List<ResultsItem>) {
                val tvList = ArrayList<TvEntity>()
                for (response in data) {
                    val tv = TvEntity(
                        response.id!!,
                        posterThumbnail = response.posterPath,
                        favorite = false,
                        response.voteAverage?.toInt() ?: 0
                    )
                    tvList.add(tv)
                }
                localDataSource.insertTv(tvList)
            }
        }.asLiveData()
    }

    override fun getTvWithDetail(tvId: Int): LiveData<Resource<TvWithDetail>> {
        return object : NetworkBoundResource<TvWithDetail, DetailTvResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvWithDetail> =
                localDataSource.getTvWithDetail(tvId)


            override fun shouldFetch(data: TvWithDetail?): Boolean =
                data?.mDetailTv == null

            override fun createCall(): LiveData<ApiResponse<DetailTvResponse>> =
                remoteDataSource.getDetailTv(tvId)

            override fun saveCallResult(data: DetailTvResponse) {
                val score = data.voteAverage?.toInt() ?: 0
                val detailMovie = DetailTvEntity(
                    data.id!!,
                    data.id!!,
                    data.name,
                    data.backdropPath,
                    data.firstAirDate,
                    data.genres?.get(0)?.name,
                    score.times(10),
                    data.numberOfSeasons.toString() + " Season",
                    data.overview
                )
                localDataSource.insertDetailTv(detailMovie)
            }
        }.asLiveData()
    }

    override fun getCastTv(tvId: Int): LiveData<Resource<CastTvEntity>> {
        return object : NetworkBoundResource<CastTvEntity, MoviesCreditResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<CastTvEntity> =
                localDataSource.getCastTv(tvId)

            override fun shouldFetch(data: CastTvEntity?): Boolean =
                data?.castTvId == null || data.castTvId == 0

            override fun createCall(): LiveData<ApiResponse<MoviesCreditResponse>> =
                remoteDataSource.getTvCredits(tvId)

            override fun saveCallResult(data: MoviesCreditResponse) {
                val tvCredit = when (data.cast?.size) {
                    0 -> CastTvEntity(
                        null
                    )
                    1 -> {
                        CastTvEntity(
                            data.id,
                            data.cast?.get(0)?.profilePath,
                            data.cast?.get(0)?.name,
                            data.cast?.get(0)?.character
                        )
                    }
                    2 -> {
                        CastTvEntity(
                            data.id,
                            data.cast?.get(0)?.profilePath,
                            data.cast?.get(0)?.name,
                            data.cast?.get(0)?.character,
                            data.cast?.get(1)?.profilePath,
                            data.cast?.get(1)?.name,
                            data.cast?.get(1)?.character
                        )
                    }
                    else -> {
                        CastTvEntity(
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
                localDataSource.insertCastTv(tvCredit)
            }
        }.asLiveData()
    }

    override fun getFavoriteTv(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTv(), config).build()
    }

    override fun setFavoriteTv(tv: TvEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteTv(tv, state) }
    }

}