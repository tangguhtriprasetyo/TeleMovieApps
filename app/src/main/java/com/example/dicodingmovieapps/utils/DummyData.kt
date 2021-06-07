package com.example.dicodingmovieapps.utils

import com.example.dicodingmovieapps.data.source.local.entity.*
import com.example.dicodingmovieapps.data.source.remote.response.DetailMovieResponse

object DummyData {

    fun generateDataMovies(): List<MoviesEntity> {

        val movies = ArrayList<MoviesEntity>()

        movies.add(
            MoviesEntity(
                1,
                "Tom Clancy's Without Remorse",
                false
            )
        )
        movies.add(
            MoviesEntity(
                2,
                "Mortal Kombat",
                false
            )
        )
        movies.add(
            MoviesEntity(
                3,
                "Godzilla vs. Kong",
                false
            )
        )
        movies.add(
            MoviesEntity(
                4,
                "22 vs. Earth ",
                false
            )
        )
        movies.add(
            MoviesEntity(
                5,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train ",
                false
            )
        )
        movies.add(
            MoviesEntity(
                6,
                "Raya and the Last Dragon ",
                false
            )
        )
        movies.add(
            MoviesEntity(
                7,
                "Nobody",
                false
            )
        )
        movies.add(
            MoviesEntity(
                8,
                "Zack Snyder's Justice League ",
                false
            )
        )
        movies.add(
            MoviesEntity(
                9,
                "Tom & Jerry",
                false
            )
        )
        movies.add(
            MoviesEntity(
                10,
                "Chaos Walking ",
                false
            )
        )

        return movies
    }

    fun generateDataTvSeries(): List<TvEntity> {

        val tvSeries = ArrayList<TvEntity>()

        tvSeries.add(
            TvEntity(
                11,
                "The Flash",
                false
            )
        )
        tvSeries.add(
            TvEntity(
                12,
                "Grey's Anatomy",
                false
            )
        )
        tvSeries.add(
            TvEntity(
                13,
                "Riverdale",
                false
            )
        )
        tvSeries.add(
            TvEntity(
                14,
                "The Bad Batch",
                false
            )
        )
        tvSeries.add(
            TvEntity(
                15,
                "Lucifer",
                false
            )
        )
        tvSeries.add(
            TvEntity(
                16,
                "The Handmaid's Tale ",
                false
            )
        )
        tvSeries.add(
            TvEntity(
                17,
                "Game of Thrones",
                false
            )
        )
        tvSeries.add(
            TvEntity(
                18,
                "Legacies",
                false
            )
        )
        tvSeries.add(
            TvEntity(
                19,
                "The Walking Dead",
                false
            )
        )
        tvSeries.add(
            TvEntity(
                20,
                "The Good Doctor",
                false
            )
        )

        return tvSeries
    }

    fun generateMovieCredit(): CastMoviesEntity {

        return CastMoviesEntity(
            2,
            "https://www.themoviedb.org/t/p/w138_and_h175_face/eLArQ1HF3UgpCA6uRF1TQBFsMQS.jpg",
            "Danielle Rose Russell",
            "Hope Mikaelson",
            "https://www.themoviedb.org/t/p/w138_and_h175_face/mbBqJRFABxqR9OmfeYVo5xvwh4C.jpg",
            "Aria Shahghasemi",
            "Landon Kirby ",
            "https://www.themoviedb.org/t/p/w138_and_h175_face/7VKR2vroCbzVeklTzYSjnVVOwkl.jpg",
            "Matthew Davis",
            "Alaric Saltzman "
        )
    }

    fun generateDetailMovie(): ArrayList<DetailMovieResponse> {

        val movies = ArrayList<DetailMovieResponse>()

        movies.add(
            DetailMovieResponse(
                "Tom Clancy's Without Remorse",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                null,
                1,
                "Lorem Ipsum Dolor te Amet",
                110,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "2020",
                7.6

            )
        )
        return movies
    }

    private fun generateDummyDetailMovie(movieId: Int): DetailMoviesEntity {


        return DetailMoviesEntity(
            movieId,
            movieId,
            "Title",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            "2020",
            "Horror",
            70,
            "10 m",
            "Overview"

        )
    }

    fun generateDummyMovieWithDetail(movie: MoviesEntity, favorite: Boolean): MoviesWithDetail {
        movie.favorite = favorite
        return MoviesWithDetail(movie, generateDummyDetailMovie(movie.movieId))
    }

}