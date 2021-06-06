package com.example.dicodingmovieapps.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingmovieapps.R
import com.example.dicodingmovieapps.data.source.local.entity.CastMoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.MoviesWithDetail
import com.example.dicodingmovieapps.databinding.ActivityDetailBinding
import com.example.dicodingmovieapps.utils.loadImage
import com.example.dicodingmovieapps.viewmodel.ViewModelFactory
import com.example.dicodingmovieapps.vo.Status

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
        const val EXTRA_TV = "extra_tv"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataMovies: MoviesEntity
    private lateinit var detailMoviesViewModel: DetailMoviesViewModel
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        detailMoviesViewModel = ViewModelProvider(this, factory)[DetailMoviesViewModel::class.java]

        dataMovies = intent.getParcelableExtra<MoviesEntity>(EXTRA_MOVIES) as MoviesEntity
        getDetailData()

        binding.addFavorite.setOnClickListener {
            setFavorite()
        }

        binding.tvErrorMessage.setOnClickListener {
            getDetailData()
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            binding.addFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        } else {
            binding.addFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
        isFavorite = !isFavorite
    }

    private fun getDetailData() {
        if (intent.getBooleanExtra(EXTRA_TV, false)) {

//            detailMoviesViewModel.getDetailTv(dataMovies.id!!).observe(this, { detailData ->
//                if (detailData != null) {
//                    initDetailData(detailData)
//                } else {
//                    showError(true)
//                    showLoading(false)
//                }
//            })
//
//            detailMoviesViewModel.getTvCredit(dataMovies.id!!).observe(this, { tvCredit ->
//                if (tvCredit != null) {
//                    initCreditInfo(tvCredit)
//                } else {
//                    showError(true)
//                    showLoading(false)
//                }
//            })

        } else {

            Log.d("movieId: ", dataMovies.movieId.toString())
            detailMoviesViewModel.setSelectedMovies(dataMovies.movieId)
            detailMoviesViewModel.getDetailMovies().observe(this, { detailData ->
                if (detailData != null) {
                    when (detailData.status) {
                        Status.LOADING -> {
                            showError(false)
                            showLoading(true)
                        }
                        Status.SUCCESS -> if (detailData.data != null) {
                            showLoading(false)
                            initDetailData(detailData.data)
                        }
                        Status.ERROR -> {
                            showError(true)
                            showLoading(false)
                        }
                    }
                }
            })

            detailMoviesViewModel.getMovieCredit(dataMovies.movieId).observe(this, { movieCredit ->
                if (movieCredit != null) {
                    when (movieCredit.status) {
                        Status.LOADING -> {
                            showError(false)
                            showLoading(true)
                        }
                        Status.SUCCESS -> if (movieCredit.data != null) {
                            showLoading(false)
                            initCreditInfo(movieCredit.data)
                        }
                        Status.ERROR -> {
                            showError(true)
                            showLoading(false)
                        }
                    }
                }
            })
        }
    }

    private fun initDetailData(moviesEntity: MoviesWithDetail) {
        val score = "${moviesEntity.mDetailMovie?.userScore} %"

        with(binding) {

            collapseToolbar.title = moviesEntity.mMovies.title
            tvReleaseDate.text = moviesEntity.mDetailMovie?.releaseDate ?: "2020"
            tvGenre.text = moviesEntity.mDetailMovie?.genre ?: "Horror"
            icScore.progress = moviesEntity.mDetailMovie?.userScore ?: 0
            tvScore.text = score
            tvDuration.text = moviesEntity.mDetailMovie?.duration ?: "0 m"
            tvOverview.text = moviesEntity.mDetailMovie?.overview ?: "-"

            imgHeaderDetail.loadImage("https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/${moviesEntity.mDetailMovie?.posterHeader}")
        }
    }

    private fun initCreditInfo(movieCredit: CastMoviesEntity) {
        with(binding) {

            tvArtist1.text = movieCredit.artist1
            tvCast1.text = movieCredit.casting1
            tvArtist2.text = movieCredit.artist2
            tvCast2.text = movieCredit.casting2
            tvArtist3.text = movieCredit.artist3
            tvCast3.text = movieCredit.casting3

            imgCast1.loadImage("https://www.themoviedb.org/t/p/w138_and_h175_face${movieCredit.imgCast1}")
            imgCast2.loadImage("https://www.themoviedb.org/t/p/w138_and_h175_face${movieCredit.imgCast2}")
            imgCast3.loadImage("https://www.themoviedb.org/t/p/w138_and_h175_face${movieCredit.imgCast3}")
        }
        showError(false)
        showLoading(false)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showError(state: Boolean) {
        if (state) {
            binding.tvErrorMessage.visibility = View.VISIBLE
        } else {
            binding.tvErrorMessage.visibility = View.GONE
        }
    }
}