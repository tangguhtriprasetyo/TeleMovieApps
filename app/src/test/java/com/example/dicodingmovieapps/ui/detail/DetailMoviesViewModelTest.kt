package com.example.dicodingmovieapps.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.local.entity.CastMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
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
class DetailMoviesViewModelTest {

    private lateinit var viewModel: DetailMoviesViewModel
    private val dummyDetailMovies = DummyData.generateDataMovies()[0]
    private val moviesId = dummyDetailMovies.id
    private val dummyDetailTvSeries = DummyData.generateDataTvSeries()[0]
    private val tvId = dummyDetailTvSeries.id
    private val dummyCredit = DummyData.generateMovieCredit()[0]

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var detailObserver: Observer<MoviesEntity>

    @Mock
    private lateinit var creditObserver: Observer<CastMoviesEntity>

    @Before
    fun setUp() {
        viewModel = DetailMoviesViewModel(moviesRepository)
        viewModel.getDetailMovies(moviesId!!)
    }

    @Test
    fun getDetailMovies() {
        val movie = MutableLiveData<MoviesEntity>()
        movie.value = dummyDetailMovies

        `when`(moviesRepository.getDetailMovie(moviesId!!)).thenReturn(movie)

        val moviesEntity = viewModel.getDetailMovies(moviesId).value as MoviesEntity

        assertNotNull(moviesEntity)
        assertEquals(dummyDetailMovies.title, moviesEntity.title)
        assertEquals(dummyDetailMovies.userScore, moviesEntity.userScore)
        assertEquals(dummyDetailMovies.posterThumbnail, moviesEntity.posterThumbnail)
        assertEquals(dummyDetailMovies.posterHeader, moviesEntity.posterHeader)
        assertEquals(dummyDetailMovies.duration, moviesEntity.duration)
        assertEquals(dummyDetailMovies.genre, moviesEntity.genre)
        assertEquals(dummyDetailMovies.overview, moviesEntity.overview)
        assertEquals(dummyDetailMovies.releaseDate, moviesEntity.releaseDate)

        viewModel.getDetailMovies(moviesId).observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyDetailMovies)
    }

    @Test
    fun getDetailTvSeries() {
        val tvSeries = MutableLiveData<MoviesEntity>()
        tvSeries.value = dummyDetailTvSeries

        `when`(moviesRepository.getDetailTv(tvId!!)).thenReturn(tvSeries)

        val moviesEntity = viewModel.getDetailTv(tvId).value as MoviesEntity

        assertNotNull(moviesEntity)
        assertEquals(dummyDetailTvSeries.title, moviesEntity.title)
        assertEquals(dummyDetailTvSeries.userScore, moviesEntity.userScore)
        assertEquals(dummyDetailTvSeries.posterThumbnail, moviesEntity.posterThumbnail)
        assertEquals(dummyDetailTvSeries.posterHeader, moviesEntity.posterHeader)
        assertEquals(dummyDetailTvSeries.duration, moviesEntity.duration)
        assertEquals(dummyDetailTvSeries.genre, moviesEntity.genre)
        assertEquals(dummyDetailTvSeries.overview, moviesEntity.overview)
        assertEquals(dummyDetailTvSeries.releaseDate, moviesEntity.releaseDate)

        viewModel.getDetailTv(tvId).observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyDetailTvSeries)
    }

    @Test
    fun getDetailCreditMovie() {
        val credit = MutableLiveData<CastMoviesEntity>()
        credit.value = dummyCredit

        `when`(moviesRepository.getMovieCredits(moviesId!!)).thenReturn(credit)

        val castMoviesEntity = viewModel.getMovieCredit(moviesId).value as CastMoviesEntity

        assertNotNull(castMoviesEntity)
        assertEquals(dummyCredit.artist1, castMoviesEntity.artist1)
        assertEquals(dummyCredit.artist2, castMoviesEntity.artist2)
        assertEquals(dummyCredit.artist3, castMoviesEntity.artist3)
        assertEquals(dummyCredit.casting1, castMoviesEntity.casting1)
        assertEquals(dummyCredit.casting2, castMoviesEntity.casting2)
        assertEquals(dummyCredit.casting3, castMoviesEntity.casting3)
        assertEquals(dummyCredit.imgCast1, castMoviesEntity.imgCast1)
        assertEquals(dummyCredit.imgCast2, castMoviesEntity.imgCast2)
        assertEquals(dummyCredit.imgCast3, castMoviesEntity.imgCast3)

        viewModel.getMovieCredit(moviesId).observeForever(creditObserver)
        verify(creditObserver).onChanged(dummyCredit)
    }

    @Test
    fun getDetailCreditTv() {
        val credit = MutableLiveData<CastMoviesEntity>()
        credit.value = dummyCredit

        `when`(moviesRepository.getTvCredits(tvId!!)).thenReturn(credit)

        val castMoviesEntity = viewModel.getTvCredit(tvId).value as CastMoviesEntity

        assertNotNull(castMoviesEntity)
        assertEquals(dummyCredit.artist1, castMoviesEntity.artist1)
        assertEquals(dummyCredit.artist2, castMoviesEntity.artist2)
        assertEquals(dummyCredit.artist3, castMoviesEntity.artist3)
        assertEquals(dummyCredit.casting1, castMoviesEntity.casting1)
        assertEquals(dummyCredit.casting2, castMoviesEntity.casting2)
        assertEquals(dummyCredit.casting3, castMoviesEntity.casting3)
        assertEquals(dummyCredit.imgCast1, castMoviesEntity.imgCast1)
        assertEquals(dummyCredit.imgCast2, castMoviesEntity.imgCast2)
        assertEquals(dummyCredit.imgCast3, castMoviesEntity.imgCast3)

        viewModel.getTvCredit(tvId).observeForever(creditObserver)
        verify(creditObserver).onChanged(dummyCredit)
    }
}