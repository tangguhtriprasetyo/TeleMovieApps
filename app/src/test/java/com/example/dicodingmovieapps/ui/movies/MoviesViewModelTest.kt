package com.example.dicodingmovieapps.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingmovieapps.utils.DummyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel
    private val dummyMoviesData = DummyData.generateDataMovies()[0]
    private val dummyTvSeriesData = DummyData.generateDataTvSeries()[0]

    @Before
    fun setUp() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun getDataMovies() {
        val moviesEntities = viewModel.getDataMovies()
        viewModel.setData(1)

        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.value?.size)
        assertEquals(dummyMoviesData.title, moviesEntities.value?.get(0)?.title)
        assertEquals(dummyMoviesData.posterThumbnail, moviesEntities.value?.get(0)?.posterThumbnail)
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