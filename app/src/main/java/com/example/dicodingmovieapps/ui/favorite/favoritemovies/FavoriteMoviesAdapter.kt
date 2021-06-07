package com.example.dicodingmovieapps.ui.favorite.favoritemovies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingmovieapps.data.source.local.entity.MoviesEntity
import com.example.dicodingmovieapps.databinding.ItemsMoviesBinding
import com.example.dicodingmovieapps.utils.loadImage

class FavoriteMovieAdapter(private val moviesClickCallback: FavoriteMoviesClickCallback) :
    PagedListAdapter<MoviesEntity, FavoriteMovieAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemsMoviesBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }

    inner class MoviesViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MoviesEntity) {
            with(binding) {
                itemView.setOnClickListener {
                    moviesClickCallback.onItemMovieClicked(movies)
                }

                imgMovies.loadImage("https://image.tmdb.org/t/p/w600_and_h900_bestv2${movies.posterThumbnail}")
                Log.d("POSTER", movies.posterThumbnail.toString())
            }
        }
    }
}