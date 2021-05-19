package com.example.dicodingmovieapps.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingmovieapps.utils.DummyData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailMoviesViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailMoviesViewModel
    private val dummyDetailMovies = DummyData.generateDataMovies()[0]
    private val dummyDetailTvSeries = DummyData.generateDataTvSeries()[0]

    @Before
    fun setUp() {
        viewModel = DetailMoviesViewModel()
    }

    @Test
    fun getDetailMovies() {
        viewModel = DetailMoviesViewModel()
        viewModel.setDetailMovies(dummyDetailMovies)

        val moviesEntity = viewModel.getDetailMovies()

        assertNotNull(moviesEntity)
        assertEquals(dummyDetailMovies.title, moviesEntity.value?.title)
        assertEquals(dummyDetailMovies.userScore, moviesEntity.value?.userScore)
        assertEquals(dummyDetailMovies.posterThumbnail, moviesEntity.value?.posterThumbnail)
        assertEquals(dummyDetailMovies.artist1, moviesEntity.value?.artist1)
        assertEquals(dummyDetailMovies.artist2, moviesEntity.value?.artist2)
        assertEquals(dummyDetailMovies.artist3, moviesEntity.value?.artist3)
        assertEquals(dummyDetailMovies.casting1, moviesEntity.value?.casting1)
        assertEquals(dummyDetailMovies.casting2, moviesEntity.value?.casting2)
        assertEquals(dummyDetailMovies.casting3, moviesEntity.value?.casting3)
        assertEquals(dummyDetailMovies.imgCast1, moviesEntity.value?.imgCast1)
        assertEquals(dummyDetailMovies.imgCast2, moviesEntity.value?.imgCast2)
        assertEquals(dummyDetailMovies.imgCast3, moviesEntity.value?.imgCast3)
        assertEquals(dummyDetailMovies.posterHeader, moviesEntity.value?.posterHeader)
        assertEquals(dummyDetailMovies.duration, moviesEntity.value?.duration)
        assertEquals(dummyDetailMovies.genre, moviesEntity.value?.genre)
        assertEquals(dummyDetailMovies.overview, moviesEntity.value?.overview)
        assertEquals(dummyDetailMovies.releaseDate, moviesEntity.value?.releaseDate)
    }

    @Test
    fun getDetailTvSeries() {
        viewModel = DetailMoviesViewModel()
        viewModel.setDetailMovies(dummyDetailTvSeries)

        val tvSeriesEntity = viewModel.getDetailMovies()

        assertNotNull(tvSeriesEntity)
        assertEquals(dummyDetailTvSeries.title, tvSeriesEntity.value?.title)
        assertEquals(dummyDetailTvSeries.userScore, tvSeriesEntity.value?.userScore)
        assertEquals(dummyDetailTvSeries.posterThumbnail, tvSeriesEntity.value?.posterThumbnail)
        assertEquals(dummyDetailTvSeries.artist1, tvSeriesEntity.value?.artist1)
        assertEquals(dummyDetailTvSeries.artist2, tvSeriesEntity.value?.artist2)
        assertEquals(dummyDetailTvSeries.artist3, tvSeriesEntity.value?.artist3)
        assertEquals(dummyDetailTvSeries.casting1, tvSeriesEntity.value?.casting1)
        assertEquals(dummyDetailTvSeries.casting2, tvSeriesEntity.value?.casting2)
        assertEquals(dummyDetailTvSeries.casting3, tvSeriesEntity.value?.casting3)
        assertEquals(dummyDetailTvSeries.imgCast1, tvSeriesEntity.value?.imgCast1)
        assertEquals(dummyDetailTvSeries.imgCast2, tvSeriesEntity.value?.imgCast2)
        assertEquals(dummyDetailTvSeries.imgCast3, tvSeriesEntity.value?.imgCast3)
        assertEquals(dummyDetailTvSeries.posterHeader, tvSeriesEntity.value?.posterHeader)
        assertEquals(dummyDetailTvSeries.duration, tvSeriesEntity.value?.duration)
        assertEquals(dummyDetailTvSeries.genre, tvSeriesEntity.value?.genre)
        assertEquals(dummyDetailTvSeries.overview, tvSeriesEntity.value?.overview)
        assertEquals(dummyDetailTvSeries.releaseDate, tvSeriesEntity.value?.releaseDate)
    }
}