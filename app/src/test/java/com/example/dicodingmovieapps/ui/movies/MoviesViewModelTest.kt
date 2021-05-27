package com.example.dicodingmovieapps.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dicodingmovieapps.data.ListMoviesEntity
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.ui.home.movies.MoviesViewModel
import com.example.dicodingmovieapps.utils.DummyData
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
    private lateinit var observer: Observer<List<ListMoviesEntity>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getListMovies() {
        val dummyMovies = DummyData.generateMovie()
        val movies = MutableLiveData<List<ListMoviesEntity>>()
        movies.value = dummyMovies

        `when`(moviesRepository.getListMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getDataMovies(1)?.value
        verify(moviesRepository).getListMovies()

        assertNotNull(moviesEntities)
        assertEquals(3, moviesEntities?.size)
        assertEquals(dummyMovies[0].title, moviesEntities?.get(0)?.title)
        assertEquals(dummyMovies[0].posterPath, moviesEntities?.get(0)?.posterPath)

        viewModel.getDataMovies(1)?.observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getListTv() {
        val dummyTvSeries = DummyData.generateMovie()
        val tvSeries = MutableLiveData<List<ListMoviesEntity>>()
        tvSeries.value = dummyTvSeries

        `when`(moviesRepository.getListTv()).thenReturn(tvSeries)
        val tvEntities = viewModel.getDataMovies(2)?.value
        verify(moviesRepository).getListTv()

        assertNotNull(tvEntities)
        assertEquals(3, tvEntities?.size)
        assertEquals(dummyTvSeries[0].title, tvEntities?.get(0)?.title)
        assertEquals(dummyTvSeries[0].posterPath, tvEntities?.get(0)?.posterPath)

        viewModel.getDataMovies(2)?.observeForever(observer)
        verify(observer).onChanged(dummyTvSeries)
    }
}