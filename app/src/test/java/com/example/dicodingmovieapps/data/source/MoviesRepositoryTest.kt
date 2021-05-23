package com.example.dicodingmovieapps.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingmovieapps.data.source.remote.RemoteDataSource
import com.example.dicodingmovieapps.utils.DummyData
import com.example.dicodingmovieapps.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val moviesRepository = FakeMoviesRepository(remote)

    private val movieTvResponse = DummyData.generateListMovie()

    private val movieDetail = DummyData.generateDetailMovie()[0]
    private val movieId = movieDetail.id

    private val tvDetail = DummyData.generateDetailTv()[0]
    private val tvId = tvDetail.id

    private val movieTvCredit = DummyData.generateMovieTvCredit()[0]
    private val movieTvCreditId = movieTvCredit.id

    @Test
    fun getListMovies() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(
                    movieTvResponse
                )
                null
            }.`when`(remote).getMoviesList(any())
        }

        val movieData = LiveDataTestUtil.getValue(moviesRepository.getListMovies())

        runBlocking {
            verify(remote).getMoviesList(any())
        }

        assertNotNull(movieData)
        assertEquals(movieTvResponse.size.toLong(), movieData.size.toLong())
    }

    @Test
    fun getListTv() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(
                    movieTvResponse
                )
                null
            }.`when`(remote).getTvList(any())
        }

        val tvData = LiveDataTestUtil.getValue(moviesRepository.getListTv())

        runBlocking {
            verify(remote).getTvList(any())
        }

        assertNotNull(tvData)
        assertEquals(movieTvResponse.size.toLong(), tvData.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadDetailMovieCallback).onDetailMovieReceived(
                    movieDetail
                )
                null
            }.`when`(remote).getDetailMovie(eq(movieId)!!, any())
        }

        val detailMovieData = LiveDataTestUtil.getValue(moviesRepository.getDetailMovie(movieId!!))

        runBlocking {
            verify(remote).getDetailMovie(eq(movieId), any())
        }

        assertNotNull(detailMovieData)
        assertEquals(movieDetail.id, detailMovieData.id)
    }

    @Test
    fun getDetailTv() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadDetailTvCallback).onDetailTvReceived(
                    tvDetail
                )
                null
            }.`when`(remote).getDetailTv(eq(tvId)!!, any())
        }

        val detailTvData = LiveDataTestUtil.getValue(moviesRepository.getDetailTv(tvId!!))

        runBlocking {
            verify(remote).getDetailTv(eq(tvId), any())
        }

        assertNotNull(detailTvData)
        assertEquals(tvDetail.id, detailTvData.id)
    }

    @Test
    fun getMovieCredits() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieCredits).onMovieCreditReceived(
                    movieTvCredit
                )
                null
            }.`when`(remote).getMovieCredits(eq(movieTvCreditId)!!, any())
        }

        val movieCreditData =
            LiveDataTestUtil.getValue(moviesRepository.getMovieCredits(movieTvCreditId!!))

        runBlocking {
            verify(remote).getMovieCredits(eq(movieTvCreditId), any())
        }

        assertNotNull(movieCreditData)
        assertEquals(movieTvCredit.id, movieCreditData.id)
    }

    @Test
    fun getTvCredits() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvCredits).onTvCreditReceived(
                    movieTvCredit
                )
                null
            }.`when`(remote).getTvCredits(eq(movieTvCreditId)!!, any())
        }

        val tvCreditData =
            LiveDataTestUtil.getValue(moviesRepository.getTvCredits(movieTvCreditId!!))

        runBlocking {
            verify(remote).getTvCredits(eq(movieTvCreditId), any())
        }

        assertNotNull(tvCreditData)
        assertEquals(movieTvCredit.id, tvCreditData.id)
    }
}