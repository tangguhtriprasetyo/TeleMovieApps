package com.example.dicodingmovieapps.ui.home.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingmovieapps.data.source.local.entity.TvEntity
import com.example.dicodingmovieapps.databinding.ItemsMoviesBinding
import com.example.dicodingmovieapps.ui.home.movies.TvAdapter.TvViewHolder
import com.example.dicodingmovieapps.utils.loadImage

class TvAdapter(private val moviesClickCallback: MoviesClickCallback) :
    PagedListAdapter<TvEntity, TvViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsMoviesBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    inner class TvViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvEntity) {
            with(binding) {
                itemView.setOnClickListener {
                    moviesClickCallback.onItemTvClicked(tv)
                }

                imgMovies.loadImage("https://image.tmdb.org/t/p/w600_and_h900_bestv2${tv.posterThumbnail}")
                Log.d("POSTER", tv.posterThumbnail.toString())
            }
        }
    }
}