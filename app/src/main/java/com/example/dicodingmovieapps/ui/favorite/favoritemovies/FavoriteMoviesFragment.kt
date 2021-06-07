package com.example.dicodingmovieapps.ui.favorite.favoritemovies

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.TvEntity
import com.example.dicodingmovieapps.databinding.FragmentFavoriteMoviesBinding
import com.example.dicodingmovieapps.ui.detail.DetailActivity
import com.example.dicodingmovieapps.viewmodel.ViewModelFactory

class FavoriteMoviesFragment : Fragment(), FavoriteMoviesClickCallback {

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): FavoriteMoviesFragment {
            return FavoriteMoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    private lateinit var favoriteMoviesViewModel: FavoriteMoviesViewModel
    private lateinit var binding: FragmentFavoriteMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showLoading(true)
            Log.d(TAG, requireActivity()::class.java.simpleName.toString())
            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteMoviesViewModel =
                ViewModelProvider(this, factory)[FavoriteMoviesViewModel::class.java]
            getTabs()
        }

        binding.tvErrorMessage.setOnClickListener {
            getTabs()
        }
    }

    private fun getTabs() {
        when (arguments?.get(ARG_SECTION_NUMBER)) {
            1 -> setDataMovies()
            2 -> setDataTv()
            else -> {
                showError(true)
                showLoading(false)
            }
        }
    }

    private fun setDataMovies() {
        Log.d("setFavMovies: ", arguments?.get(ARG_SECTION_NUMBER).toString())
        val favoriteMoviesAdapter = FavoriteMovieAdapter(this@FavoriteMoviesFragment)
        favoriteMoviesViewModel.getFavoriteMovies((arguments?.get(ARG_SECTION_NUMBER) ?: 1) as Int)
            ?.observe(viewLifecycleOwner, { moviesData ->
                if (moviesData != null) {
                    if (moviesData.isEmpty()) {
                        showError(true)
                        showLoading(false)
                    } else {
                        favoriteMoviesAdapter.submitList(moviesData)
                        with(binding.rvMovies) {
                            layoutManager = GridLayoutManager(context, 2)
                            setHasFixedSize(true)
                            adapter = favoriteMoviesAdapter
                        }
                        showLoading(false)
                        showError(false)
                    }
                } else {
                    showLoading(false)
                    showError(true)
                }
            })
    }

    private fun setDataTv() {
        Log.d("setFavTv: ", arguments?.get(ARG_SECTION_NUMBER).toString())
        val favoriteTvAdapter = FavoriteTvAdapter(this@FavoriteMoviesFragment)
        favoriteMoviesViewModel.getFavoriteTv((arguments?.get(ARG_SECTION_NUMBER) ?: 2) as Int)
            ?.observe(viewLifecycleOwner, { tvData ->
                if (tvData != null) {
                    if (tvData.isEmpty()) {
                        showLoading(false)
                        showError(true)
                    } else {
                        favoriteTvAdapter.submitList(tvData)
                        with(binding.rvMovies) {
                            layoutManager = GridLayoutManager(context, 2)
                            setHasFixedSize(true)
                            adapter = favoriteTvAdapter
                        }
                        showLoading(false)
                        showError(false)
                    }
                } else {
                    showLoading(false)
                    showError(true)
                }
            })
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
            binding.rvMovies.visibility = View.GONE
        } else {
            binding.tvErrorMessage.visibility = View.GONE
            binding.rvMovies.visibility = View.VISIBLE
        }
    }

    override fun onItemMovieClicked(movies: MoviesEntity) {
        val isTv = false
        Log.d("onItemMovieClicked: ", isTv.toString())
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIES, movies)
        intent.putExtra(DetailActivity.EXTRA_IS_TV, isTv)
        startActivity(intent)
    }

    override fun onItemTvClicked(tv: TvEntity) {
        val isTv = true
        Log.d("onItemTvClicked: ", isTv.toString())
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_TV, tv)
        intent.putExtra(DetailActivity.EXTRA_IS_TV, isTv)
        startActivity(intent)
    }
}