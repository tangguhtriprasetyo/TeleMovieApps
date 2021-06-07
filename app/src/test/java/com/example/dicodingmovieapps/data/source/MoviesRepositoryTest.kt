package com.example.dicodingmovieapps.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.dicodingmovieapps.data.source.local.LocalDataSource
import com.example.dicodingmovieapps.data.source.local.entity.CastMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesWithDetail
import com.example.dicodingmovieapps.data.source.local.entity.TvEntity
import com.example.dicodingmovieapps.data.source.remote.RemoteDataSource
import com.example.dicodingmovieapps.utils.*
import com.example.dicodingmovieapps.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val moviesRepository = FakeMoviesRepository(remote, local, appExecutors)

    private val movieResponse = DummyData.generateDataMovies()

    private val tvResponse = DummyData.generateDataTvSeries()

    private val movieDetail = DummyData.generateDetailMovie()[0]
    private val movieId = movieDetail.id

    @Test
    fun getListMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java)
        Mockito.`when`(local.getAllMovies(SortUtils.DEFAULT))
            .thenReturn(dataSourceFactory as DataSource.Factory<Int, MoviesEntity>?)
        moviesRepository.getListMovies(SortUtils.DEFAULT)

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyData.generateDataMovies()))
        verify(local).getAllMovies(SortUtils.DEFAULT)
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getListTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java)
        Mockito.`when`(local.getAllTv(SortUtils.DEFAULT))
            .thenReturn(dataSourceFactory as DataSource.Factory<Int, TvEntity>?)
        moviesRepository.getListTv(SortUtils.DEFAULT)

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DummyData.generateDataTvSeries()))
        verify(local).getAllTv(SortUtils.DEFAULT)
        assertNotNull(movieEntities.data)
        assertEquals(tvResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieWithDetail() {
        val dummyEntity = MutableLiveData<MoviesWithDetail>()
        dummyEntity.value =
            DummyData.generateDummyMovieWithDetail(DummyData.generateDataMovies()[0], false)
        Mockito.`when`(local.getMovieWithDetail(movieId!!)).thenReturn(dummyEntity)
        val moviesEntities = LiveDataTestUtil.getValue(moviesRepository.getMovieWithDetail(movieId))
        verify(local).getMovieWithDetail(movieId)
        assertNotNull(moviesEntities.data)
        assertNotNull(moviesEntities.data?.mDetailMovie?.title)
    }

    @Test
    fun getDetailTv() {
        val dummyEntity = MutableLiveData<MoviesWithDetail>()
        dummyEntity.value =
            DummyData.generateDummyMovieWithDetail(DummyData.generateDataMovies()[0], false)
        Mockito.`when`(local.getMovieWithDetail(movieId!!)).thenReturn(dummyEntity)
        val moviesEntities = LiveDataTestUtil.getValue(moviesRepository.getMovieWithDetail(movieId))
        verify(local).getMovieWithDetail(movieId)
        assertNotNull(moviesEntities.data)
        assertNotNull(moviesEntities.data?.mDetailMovie?.title)
    }

    @Test
    fun getMovieCredits() {
        val dummyEntity = MutableLiveData<CastMoviesEntity>()
        dummyEntity.value = DummyData.generateMovieCredit()
        Mockito.`when`(local.getCastMovie(movieId!!)).thenReturn(dummyEntity)
        val moviesEntities = LiveDataTestUtil.getValue(moviesRepository.getCastMovie(movieId))
        verify(local).getCastMovie(movieId)
        assertNotNull(moviesEntities.data)
    }

    @Test
    fun getTvCredits() {
        val dummyEntity = MutableLiveData<CastMoviesEntity>()
        dummyEntity.value = DummyData.generateMovieCredit()
        Mockito.`when`(local.getCastMovie(movieId!!)).thenReturn(dummyEntity)
        val moviesEntities = LiveDataTestUtil.getValue(moviesRepository.getCastMovie(movieId))
        verify(local).getCastMovie(movieId)
        assertNotNull(moviesEntities.data)
    }
}