package com.example.dicodingmovieapps.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.dicodingmovieapps.data.MoviesEntity
import com.example.dicodingmovieapps.data.source.MoviesRepository
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
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesEntity>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun getDataMovies() {
        val dummyMovies = DummyData.generateDataMovies()
        val movies = MutableLiveData<List<MoviesEntity>>()
        movies.value = dummyMovies

        `when`(moviesRepository.getListMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getDataMovies(1)?.value
        verify(moviesRepository).getListMovies()

        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.value?.size)
        assertEquals(dummyMoviesData.title, moviesEntities.value?.get(0)?.title)
        assertEquals(dummyMoviesData.posterThumbnail, moviesEntities.value?.get(0)?.posterThumbnail)

        viewModel.getDataMovies(1)?.observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getDataTvSeries() {
        val tvSeriesEntities = viewModel.getDataMovies()
        viewModel.setData(2)

        assertNotNull(tvSeriesEntities)
        assertEquals(10, tvSeriesEntities.value?.size)
        assertEquals(dummyTvSeriesData.title, tvSeriesEntities.value?.get(0)?.title)
        assertEquals(dummyTvSeriesData.posterThumbnail, tvSeriesEntities.value?.get(0)?.posterThumbnail)
    }
}