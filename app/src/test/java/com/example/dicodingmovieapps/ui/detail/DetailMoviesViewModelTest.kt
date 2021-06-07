package com.example.dicodingmovieapps.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dicodingmovieapps.data.source.MoviesRepository
import com.example.dicodingmovieapps.data.source.local.entity.CastMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesWithDetail
import com.example.dicodingmovieapps.utils.DummyData
import com.example.dicodingmovieapps.vo.Resource
import com.nhaarman.mockitokotlin2.verify
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
    private val moviesId = dummyDetailMovies.movieId

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var detailObserver: Observer<Resource<MoviesWithDetail>>

    @Mock
    private lateinit var creditObserver: Observer<Resource<CastMoviesEntity>>

    @Before
    fun setUp() {
        viewModel = DetailMoviesViewModel(moviesRepository)
        viewModel.getDetailMovies(moviesId)
    }

    @Test
    fun getDetailMovies() {
        val dummyMovieWithDetail =
            Resource.success(DummyData.generateDummyMovieWithDetail(dummyDetailMovies, true))
        val movie = MutableLiveData<Resource<MoviesWithDetail>>()
        movie.value = dummyMovieWithDetail

        `when`(moviesRepository.getMovieWithDetail(moviesId)).thenReturn(movie)

        viewModel.getDetailMovies(moviesId).observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyMovieWithDetail)
    }

    @Test
    fun getDetailTvSeries() {
        val dummyTvWithDetail =
            Resource.success(DummyData.generateDummyMovieWithDetail(dummyDetailMovies, true))
        val movie = MutableLiveData<Resource<MoviesWithDetail>>()
        movie.value = dummyTvWithDetail

        `when`(moviesRepository.getMovieWithDetail(moviesId)).thenReturn(movie)

        viewModel.getDetailMovies(moviesId).observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyTvWithDetail)
    }

    @Test
    fun getDetailCreditMovie() {
        val dummyCreditMovie = Resource.success(DummyData.generateMovieCredit())
        val movie = MutableLiveData<Resource<CastMoviesEntity>>()
        movie.value = dummyCreditMovie

        `when`(moviesRepository.getCastMovie(moviesId)).thenReturn(movie)

        viewModel.getMovieCredit(moviesId).observeForever(creditObserver)
        verify(creditObserver).onChanged(dummyCreditMovie)
    }

    @Test
    fun getDetailCreditTv() {
        val dummyCreditTv = Resource.success(DummyData.generateMovieCredit())
        val movie = MutableLiveData<Resource<CastMoviesEntity>>()
        movie.value = dummyCreditTv

        `when`(moviesRepository.getCastMovie(moviesId)).thenReturn(movie)

        viewModel.getMovieCredit(moviesId).observeForever(creditObserver)
        verify(creditObserver).onChanged(dummyCreditTv)
    }
}