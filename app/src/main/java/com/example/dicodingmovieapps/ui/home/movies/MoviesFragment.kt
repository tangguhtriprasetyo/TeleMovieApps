package com.example.dicodingmovieapps.ui.home.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.databinding.FragmentMoviesBinding
import com.example.dicodingmovieapps.ui.detail.DetailActivity
import com.example.dicodingmovieapps.viewmodel.ViewModelFactory
import com.example.dicodingmovieapps.vo.Status


class MoviesFragment : Fragment(), MoviesClickCallback {

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): MoviesFragment {
            return MoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            moviesViewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
            setDataMovies()
        }

        binding.tvErrorMessage.setOnClickListener {
            setDataMovies()
        }
    }

    private fun setDataMovies() {
        val moviesAdapter = MoviesAdapter(this@MoviesFragment)
        moviesViewModel.getDataMovies((arguments?.get(ARG_SECTION_NUMBER) ?: 1) as Int)
            ?.observe(viewLifecycleOwner, { moviesData ->
                if (moviesData != null) {
                    when (moviesData.status) {
                        Status.LOADING -> {
                            showLoading(true)
                            showError(false)
                        }
                        Status.SUCCESS -> {
                            showLoading(false)
                            moviesAdapter.submitList(moviesData.data)
                        }
                        Status.ERROR -> {
                            showError(true)
                            showLoading(false)
                        }
                    }
                }
            })
        with(binding.rvMovies) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = moviesAdapter
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

    override fun onItemClicked(movies: MoviesEntity) {
        val isTv = arguments?.get(ARG_SECTION_NUMBER) ?: 1 != 1
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIES, movies)
        intent.putExtra(DetailActivity.EXTRA_TV, isTv)
        startActivity(intent)
    }
}