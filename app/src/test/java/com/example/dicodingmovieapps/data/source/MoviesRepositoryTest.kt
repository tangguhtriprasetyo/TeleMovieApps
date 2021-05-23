package com.example.dicodingmovieapps.data.source

import com.example.dicodingmovieapps.data.source.remote.RemoteDataSource
import com.example.dicodingmovieapps.utils.DummyData
import com.example.dicodingmovieapps.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito

class MoviesRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val moviesRepository = FakeMoviesRepository(remote)

    private val movieResponse = DummyData.generateDataMovies()
    private val movieId = movieResponse[0].id

    private val tvResponse = DummyData.generateDataTvSeries()
    private val tvId = tvResponse[0].id

    private val movieCredit = DummyData.generateMovieCredit()

    @Test
    suspend fun getListMovies() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(
                    movieResponse
                )
                null
            }.`when`(remote).getMoviesList(any())
        }

        val movieData = LiveDataTestUtil.getValue(moviesRepository.getListMovies())

        runBlocking {
            verify(remote).getMoviesList(any())
        }

        assertNotNull(movieData)
        assertEquals(movieResponse.size.toLong(), movieData.size.toLong())
    }

    @Test
    fun getListTv() {
    }
}