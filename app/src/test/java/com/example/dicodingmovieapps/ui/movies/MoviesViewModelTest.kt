package com.example.dicodingmovieapps.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.TvEntity
import com.example.dicodingmovieapps.ui.home.movies.MoviesViewModel
import com.example.dicodingmovieapps.utils.SortUtils
import com.example.dicodingmovieapps.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var observerTv: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Mock
    private lateinit var pagedListTv: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getListMovies() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        movies.value = dummyMovies

        `when`(moviesRepository.getListMovies(SortUtils.DEFAULT)).thenReturn(movies)
        val moviesEntities = viewModel.getDataMovies(1, SortUtils.DEFAULT)?.value?.data
        verify(moviesRepository).getListMovies(SortUtils.DEFAULT)

        assertNotNull(moviesEntities)
        assertEquals(5, moviesEntities?.size)

        viewModel.getDataMovies(1, SortUtils.DEFAULT)?.observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getListTv() {
        val dummyTvSeries = Resource.success(pagedListTv)
        `when`(dummyTvSeries.data?.size).thenReturn(5)
        val tvSeries = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tvSeries.value = dummyTvSeries

        `when`(moviesRepository.getListTv(SortUtils.DEFAULT)).thenReturn(tvSeries)
        val tvEntities = viewModel.getDataTv(2, SortUtils.DEFAULT)?.value?.data
        verify(moviesRepository).getListTv(SortUtils.DEFAULT)

        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getDataTv(2, SortUtils.DEFAULT)?.observeForever(observerTv)
        verify(observerTv).onChanged(dummyTvSeries)
    }
}