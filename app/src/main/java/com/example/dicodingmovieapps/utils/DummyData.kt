package com.example.dicodingmovieapps.utils

import com.example.dicodingmovieapps.data.source.local.entity.CastMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.remote.response.DetailMovieResponse
import com.example.dicodingmovieapps.data.source.remote.response.DetailTvResponse
import com.example.dicodingmovieapps.data.source.remote.response.MoviesCreditResponse
import com.example.dicodingmovieapps.data.source.remote.response.ResultsItem

object DummyData {

    fun generateDataMovies(): List<MoviesEntity> {

        val movies = ArrayList<MoviesEntity>()

        movies.add(
            MoviesEntity(
                1,
                "Tom Clancy's Without Remorse",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                false
            )
        )
        movies.add(
            MoviesEntity(
                2,
                "Mortal Kombat",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                false
            )
        )
        movies.add(
            MoviesEntity(
                3,
                "Godzilla vs. Kong",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                false
            )
        )
        movies.add(
            MoviesEntity(
                4,
                "22 vs. Earth ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/32vLDKSzcCMh55zqqaSqqUA8naz.jpg",
                false
            )
        )
        movies.add(
            MoviesEntity(
                5,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                false
            )
        )
        movies.add(
            MoviesEntity(
                6,
                "Raya and the Last Dragon ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                false
            )
        )
        movies.add(
            MoviesEntity(
                7,
                "Nobody",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                false
            )
        )
        movies.add(
            MoviesEntity(
                8,
                "Zack Snyder's Justice League ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                false
            )
        )
        movies.add(
            MoviesEntity(
                9,
                "Tom & Jerry",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8XZI9QZ7Pm3fVkigWJPbrXCMzjq.jpg",
                false
            )
        )
        movies.add(
            MoviesEntity(
                10,
                "Chaos Walking ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                false
            )
        )

        return movies
    }

    fun generateDataTvSeries(): List<MoviesEntity> {

        val tvSeries = ArrayList<MoviesEntity>()

        tvSeries.add(
            MoviesEntity(
                11,
                "The Flash",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                false
            )
        )
        tvSeries.add(
            MoviesEntity(
                12,
                "Grey's Anatomy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                false
            )
        )
        tvSeries.add(
            MoviesEntity(
                13,
                "Riverdale",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                false
            )
        )
        tvSeries.add(
            MoviesEntity(
                14,
                "The Bad Batch",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                false
            )
        )
        tvSeries.add(
            MoviesEntity(
                15,
                "Lucifer",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                false
            )
        )
        tvSeries.add(
            MoviesEntity(
                16,
                "The Handmaid's Tale ",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oIkxqt6ug5zT5ZSUUyc1Iqopf02.jpg",
                false
            )
        )
        tvSeries.add(
            MoviesEntity(
                17,
                "Game of Thrones",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                false
            )
        )
        tvSeries.add(
            MoviesEntity(
                18,
                "Legacies",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qTZIgXrBKURBK1KrsT7fe3qwtl9.jpg",
                false
            )
        )
        tvSeries.add(
            MoviesEntity(
                19,
                "The Walking Dead",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                false
            )
        )
        tvSeries.add(
            MoviesEntity(
                20,
                "The Good Doctor",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                false
            )
        )

        return tvSeries
    }

    fun generateMovieCredit(): List<CastMoviesEntity> {

        val movieCredit = ArrayList<CastMoviesEntity>()

        movieCredit.add(
            CastMoviesEntity(
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
        )

        return movieCredit
    }

    fun generateListMovie(): List<ResultsItem> {

        val listMovie = ArrayList<ResultsItem>()

        listMovie.add(
            ResultsItem(
                20,
                "The Good Doctor",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                "2017",
                7.6,
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
            )
        )

        return listMovie
    }

    fun generateDetailMovie(): List<DetailMovieResponse> {

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

    fun generateDetailTv(): List<DetailTvResponse> {

        val movies = ArrayList<DetailTvResponse>()

        movies.add(
            DetailTvResponse(
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                null,
                36,
                2,
                "2009",
                "Lorem Ipsum Dolor te Amet",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                6.6,
                "The Flash"
            )
        )

        return movies
    }

    fun generateMovieTvCredit(): List<MoviesCreditResponse> {

        val creditMovieTv = ArrayList<MoviesCreditResponse>()

        creditMovieTv.add(
            MoviesCreditResponse(
                null,
                2
            )
        )

        return creditMovieTv
    }
}