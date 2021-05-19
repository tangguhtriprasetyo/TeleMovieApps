package com.example.dicodingmovieapps.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dicodingmovieapps.databinding.FragmentMoviesBinding


class MoviesFragment : Fragment() {

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
        setDataMovies()
        if (activity != null) {
            moviesViewModel.getDataMovies().observe(viewLifecycleOwner, { moviesData ->
                if (moviesData != null) {
                    val moviesAdapter = MoviesAdapter()
                    moviesAdapter.setMovies(moviesData)

                    with(binding.rvMovies) {
                        layoutManager = GridLayoutManager(context, 2)
                        setHasFixedSize(true)
                        adapter = moviesAdapter
                    }
                    showLoading(false)
                    showError(false)
                } else {
                    showError(true)
                    showLoading(false)
                }
            })
        }

        binding.tvErrorMessage.setOnClickListener {
            setDataMovies()
        }
    }

    private fun setDataMovies() {
        showError(false)
        showLoading(true)
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java).apply {
            setData(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
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