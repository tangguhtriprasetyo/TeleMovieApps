package com.example.dicodingmovieapps.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingmovieapps.R
import com.example.dicodingmovieapps.data.CastMoviesEntity
import com.example.dicodingmovieapps.data.ListMoviesEntity
import com.example.dicodingmovieapps.data.MoviesEntity
import com.example.dicodingmovieapps.databinding.ActivityDetailBinding
import com.example.dicodingmovieapps.utils.loadImage
import com.example.dicodingmovieapps.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
        const val EXTRA_TV = "extra_tv"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataMovies: ListMoviesEntity
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

        dataMovies = intent.getParcelableExtra<ListMoviesEntity>(EXTRA_MOVIES) as ListMoviesEntity
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
        showError(false)
        showLoading(true)
        if (intent.getBooleanExtra(EXTRA_TV, false)) {

            detailMoviesViewModel.getDetailTv(dataMovies.id).observe(this, { detailData ->
                if (detailData != null) {
                    initDetailData(detailData)
                } else {
                    showError(true)
                    showLoading(false)
                }
            })

            detailMoviesViewModel.getTvCredit(dataMovies.id).observe(this, { tvCredit ->
                if (tvCredit != null) {
                    initCreditInfo(tvCredit)
                } else {
                    showError(true)
                    showLoading(false)
                }
            })

        } else {

            detailMoviesViewModel.getDetailMovies(dataMovies.id).observe(this, { detailData ->
                if (detailData != null) {
                    initDetailData(detailData)
                } else {
                    showError(true)
                    showLoading(false)
                }
            })

            detailMoviesViewModel.getMovieCredit(dataMovies.id).observe(this, { movieCredit ->
                if (movieCredit != null) {
                    initCreditInfo(movieCredit)
                } else {
                    showError(true)
                    showLoading(false)
                }
            })
        }
    }

    private fun initDetailData(moviesEntity: MoviesEntity) {
        val score = "${moviesEntity.userScore} %"

        with(binding) {

            collapseToolbar.title = moviesEntity.title
            tvReleaseDate.text = moviesEntity.releaseDate
            tvGenre.text = moviesEntity.genre
            icScore.progress = moviesEntity.userScore
            tvScore.text = score
            tvDuration.text = moviesEntity.duration
            tvOverview.text = moviesEntity.overview

            imgHeaderDetail.loadImage("https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/${moviesEntity.posterHeader}")
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