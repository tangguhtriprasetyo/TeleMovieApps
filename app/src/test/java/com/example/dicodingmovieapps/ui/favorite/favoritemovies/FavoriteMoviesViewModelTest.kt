package com.example.dicodingmovieapps.ui.favorite.favoritemovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.TvEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FavoriteMoviesViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteMoviesViewModel

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var observerTv: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Mock
    private lateinit var pagedListTv: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteMoviesViewModel(moviesRepository)
    }

    @Test
    fun getListMovies() {
        val dummyMovies = pagedList
        Mockito.`when`(dummyMovies.size).thenReturn(1)
        val movies = MutableLiveData<PagedList<MoviesEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(moviesRepository.getFavoriteMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getFavoriteMovies(1)?.value
        verify(moviesRepository).getFavoriteMovies()

        assertNotNull(moviesEntities)
        assertEquals(1, moviesEntities?.size)

        viewModel.getFavoriteMovies(1)?.observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getListTv() {
        val dummyTvSeries = pagedListTv
        Mockito.`when`(dummyTvSeries.size).thenReturn(1)
        val tvSeries = MutableLiveData<PagedList<TvEntity>>()
        tvSeries.value = dummyTvSeries

        Mockito.`when`(moviesRepository.getFavoriteTv()).thenReturn(tvSeries)
        val tvEntities = viewModel.getFavoriteTv(2)?.value
        verify(moviesRepository).getFavoriteTv()

        assertNotNull(tvEntities)
        assertEquals(1, tvEntities?.size)

        viewModel.getFavoriteTv(2)?.observeForever(observerTv)
        verify(observerTv).onChanged(dummyTvSeries)
    }
}