package com.example.dicodingmovieapps.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingmovieapps.data.MoviesEntity
import com.example.dicodingmovieapps.databinding.ActivityDetailBinding
import com.example.dicodingmovieapps.utils.loadImage

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataMovies: MoviesEntity
    private lateinit var detailMoviesViewModel: DetailMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dataMovies = intent.getParcelableExtra<MoviesEntity>(EXTRA_MOVIES) as MoviesEntity
        setDetailData()
        initView()
    }

    private fun setDetailData() {
        showError(false)
        showLoading(true)
        detailMoviesViewModel = ViewModelProvider(this).get(DetailMoviesViewModel::class.java).apply {
            setDetailMovies(dataMovies)
        }
    }

    private fun initView() {
        detailMoviesViewModel.getDetailMovies().observe(this, {detailMovies ->
            if (detailMovies != null) {
                val score = "${detailMovies.userScore} %"

                with(binding) {
                    collapseToolbar.title = detailMovies.title
                    tvReleaseDate.text = detailMovies.releaseDate
                    tvGenre.text = detailMovies.genre
                    icScore.progress = detailMovies.userScore
                    tvScore.text = score
                    tvDuration.text = detailMovies.duration
                    tvOverview.text = detailMovies.overview
                    tvArtist1.text = detailMovies.artist1
                    tvCast1.text = detailMovies.casting1
                    tvArtist2.text = detailMovies.artist2
                    tvCast2.text = detailMovies.casting2
                    tvArtist3.text = detailMovies.artist3
                    tvCast3.text = detailMovies.casting3

                    imgHeaderDetail.loadImage(detailMovies.posterHeader)
                    imgCast1.loadImage(detailMovies.imgCast1)
                    imgCast2.loadImage(detailMovies.imgCast2)
                    imgCast3.loadImage(detailMovies.imgCast3)
                }
                showLoading(false)
                showError(false)
            } else {
                showError(true)
                showLoading(false)
            }
        })

        binding.tvErrorMessage.setOnClickListener {
            setDetailData()
        }
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