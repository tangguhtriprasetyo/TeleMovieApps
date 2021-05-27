package com.example.dicodingmovieapps.ui.home.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingmovieapps.data.ListMoviesEntity
import com.example.dicodingmovieapps.databinding.ItemsMoviesBinding
import com.example.dicodingmovieapps.utils.loadImage

class MoviesAdapter(private val moviesClickCallback: MoviesClickCallback) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var listMovies = ArrayList<ListMoviesEntity>()

    fun setMovies(movies: List<ListMoviesEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemsMoviesBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MoviesViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: ListMoviesEntity) {
            with(binding) {
                itemView.setOnClickListener {
                    moviesClickCallback.onItemClicked(movies)
                }

                imgMovies.loadImage("https://image.tmdb.org/t/p/w600_and_h900_bestv2${movies.posterPath}")
                Log.d("POSTER", movies.posterPath)
            }
        }

    }
}