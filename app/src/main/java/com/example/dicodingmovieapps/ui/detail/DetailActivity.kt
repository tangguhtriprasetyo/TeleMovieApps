package com.example.dicodingmovieapps.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingmovieapps.R
import com.example.dicodingmovieapps.data.source.local.entity.*
import com.example.dicodingmovieapps.databinding.ActivityDetailBinding
import com.example.dicodingmovieapps.utils.loadImage
import com.example.dicodingmovieapps.viewmodel.ViewModelFactory
import com.example.dicodingmovieapps.vo.Status

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_IS_TV = "extra_is_tv"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataMovies: MoviesEntity
    private lateinit var dataTv: TvEntity
    private lateinit var detailDataMovies: MoviesWithDetail
    private lateinit var detailDataTv: TvWithDetail
    private lateinit var detailMoviesViewModel: DetailMoviesViewModel
    private var isFavorite = false
    private var isTv = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        detailMoviesViewModel = ViewModelProvider(this, factory)[DetailMoviesViewModel::class.java]

        isTv = intent.getBooleanExtra(EXTRA_IS_TV, false)
        Log.d("isItTv ", isTv.toString())
        getDetailData()

        binding.addFavorite.setOnClickListener {
            if (isTv) {
                setFavoriteTv()
            } else {
                setFavoriteMovies()
            }

        }

        binding.tvErrorMessage.setOnClickListener {
            getDetailData()
        }
    }

    private fun setFavoriteMovies() {
        if (!detailDataMovies.mMovies.favorite) {
            Toast.makeText(this, "Added to Favorite!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Deleted from Favorite!", Toast.LENGTH_SHORT).show()
        }
        detailMoviesViewModel.setFavoriteMovie(detailDataMovies.mMovies)
    }

    private fun setFavoriteTv() {
        if (!detailDataTv.mTv.favorite) {
            Toast.makeText(this, "Added to Favorite!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Deleted from Favorite!", Toast.LENGTH_SHORT).show()
        }
        detailMoviesViewModel.setFavoriteTv(detailDataTv.mTv)
    }

    private fun getDetailData() {
        if (isTv) {
            dataTv = intent.getParcelableExtra<TvEntity>(EXTRA_TV) as TvEntity
            detailMoviesViewModel.getDetailTv(dataTv.tvId)
                .observe(this, { detailData ->
                    if (detailData != null) {
                        when (detailData.status) {
                            Status.LOADING -> {
                                showError(false)
                                showLoading(true)
                            }
                            Status.SUCCESS -> if (detailData.data != null) {
                                showLoading(false)
                                detailDataTv = detailData.data
                                initDetailDataTv(detailDataTv)
                            }
                            Status.ERROR -> {
                                showError(true)
                                showLoading(false)
                            }
                        }
                    }
                })
            detailMoviesViewModel.getTvCredit(dataTv.tvId).observe(this, { tvCredit ->
                if (tvCredit != null) {
                    when (tvCredit.status) {
                        Status.LOADING -> {
                            showError(false)
                            showLoading(true)
                        }
                        Status.SUCCESS -> if (tvCredit.data != null) {
                            showLoading(false)
                            initCreditInfoTv(tvCredit.data)
                        }
                        Status.ERROR -> {
                            showError(true)
                            showLoading(false)
                        }
                    }
                }
            })

        } else {
            dataMovies = intent.getParcelableExtra<MoviesEntity>(EXTRA_MOVIES) as MoviesEntity
            detailMoviesViewModel.getDetailMovies(dataMovies.movieId)
                .observe(this, { detailData ->
                    if (detailData != null) {
                        when (detailData.status) {
                            Status.LOADING -> {
                                showError(false)
                                showLoading(true)
                            }
                            Status.SUCCESS -> if (detailData.data != null) {
                                showLoading(false)
                                detailDataMovies = detailData.data
                                initDetailDataMovie(detailDataMovies)
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
                            initCreditInfoMovie(movieCredit.data)
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

    private fun initDetailDataMovie(moviesEntity: MoviesWithDetail) {
        val score = "${moviesEntity.mDetailMovie?.userScore} %"

        with(binding) {
            if (!moviesEntity.mMovies.favorite) {
                binding.addFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            } else {
                binding.addFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }

            collapseToolbar.title = moviesEntity.mDetailMovie?.title
            tvReleaseDate.text = moviesEntity.mDetailMovie?.releaseDate ?: "2020"
            tvGenre.text = moviesEntity.mDetailMovie?.genre ?: "Horror"
            icScore.progress = moviesEntity.mDetailMovie?.userScore ?: 0
            tvScore.text = score
            tvDuration.text = moviesEntity.mDetailMovie?.duration ?: "0 m"
            tvOverview.text = moviesEntity.mDetailMovie?.overview ?: "-"

            imgHeaderDetail.loadImage("https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/${moviesEntity.mDetailMovie?.posterHeader}")
        }
    }

    private fun initCreditInfoMovie(movieCredit: CastMoviesEntity) {
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

    private fun initDetailDataTv(tvEntity: TvWithDetail) {
        val score = "${tvEntity.mDetailTv?.userScore} %"

        with(binding) {
            if (!tvEntity.mTv.favorite) {
                binding.addFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            } else {
                binding.addFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }

            collapseToolbar.title = tvEntity.mDetailTv?.name
            tvReleaseDate.text = tvEntity.mDetailTv?.releaseDate ?: "2020"
            tvGenre.text = tvEntity.mDetailTv?.genre ?: "Horror"
            icScore.progress = tvEntity.mDetailTv?.userScore ?: 0
            tvScore.text = score
            tvDuration.text = tvEntity.mDetailTv?.duration ?: "0 m"
            tvOverview.text = tvEntity.mDetailTv?.overview ?: "-"

            imgHeaderDetail.loadImage("https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/${tvEntity.mDetailTv?.posterHeader}")
        }
    }

    private fun initCreditInfoTv(tvCredit: CastTvEntity) {
        with(binding) {

            tvArtist1.text = tvCredit.artist1
            tvCast1.text = tvCredit.casting1
            tvArtist2.text = tvCredit.artist2
            tvCast2.text = tvCredit.casting2
            tvArtist3.text = tvCredit.artist3
            tvCast3.text = tvCredit.casting3

            imgCast1.loadImage("https://www.themoviedb.org/t/p/w138_and_h175_face${tvCredit.imgCast1}")
            imgCast2.loadImage("https://www.themoviedb.org/t/p/w138_and_h175_face${tvCredit.imgCast2}")
            imgCast3.loadImage("https://www.themoviedb.org/t/p/w138_and_h175_face${tvCredit.imgCast3}")
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