package com.example.dicodingmovieapps.ui.home.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dicodingmovieapps.R
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.data.source.local.entity.TvEntity
import com.example.dicodingmovieapps.databinding.FragmentMoviesBinding
import com.example.dicodingmovieapps.ui.detail.DetailActivity
import com.example.dicodingmovieapps.utils.SortUtils
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
            getTabs()
        }

        binding.tvErrorMessage.setOnClickListener {
            getTabs()
        }
    }

    private fun getTabs() {
        when (arguments?.get(ARG_SECTION_NUMBER)) {
            1 -> setDataMovies(SortUtils.DEFAULT)
            2 -> setDataTv(SortUtils.DEFAULT)
            else -> {
                showError(true)
                showLoading(false)
            }
        }
    }

    private fun setDataMovies(sortQuery: String) {
        Log.d("setDataMovies: ", arguments?.get(ARG_SECTION_NUMBER).toString())
        val moviesAdapter = MoviesAdapter(this@MoviesFragment)
        moviesViewModel.getDataMovies((arguments?.get(ARG_SECTION_NUMBER) ?: 1) as Int, sortQuery)
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
                            Log.d("movieAdapter: ", moviesData.data.toString())
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

    private fun setDataTv(sortQuery: String) {
        Log.d("setDataTv: ", arguments?.get(ARG_SECTION_NUMBER).toString())
        val tvAdapter = TvAdapter(this@MoviesFragment)
        moviesViewModel.getDataTv((arguments?.get(ARG_SECTION_NUMBER) ?: 2) as Int, sortQuery)
            ?.observe(viewLifecycleOwner, { tvData ->
                if (tvData != null) {
                    when (tvData.status) {
                        Status.LOADING -> {
                            showLoading(true)
                            showError(false)
                        }
                        Status.SUCCESS -> {
                            showLoading(false)
                            tvAdapter.submitList(tvData.data)
                            Log.d("tvAdapter: ", tvData.data.toString())
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
            adapter = tvAdapter
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_default -> sort = SortUtils.DEFAULT
            R.id.action_highest -> sort = SortUtils.HIGHEST
            R.id.action_lowest -> sort = SortUtils.Lowest
        }
        if (arguments?.get(ARG_SECTION_NUMBER) == 1) {
            setDataMovies(sort)
        } else {
            setDataTv(sort)
        }
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }
}