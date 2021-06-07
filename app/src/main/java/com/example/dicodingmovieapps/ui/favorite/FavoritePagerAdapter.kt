package com.example.dicodingmovieapps.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dicodingmovieapps.ui.favorite.favoritemovies.FavoriteMoviesFragment

class FavoritePagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {

        return FavoriteMoviesFragment.newInstance(position + 1)
    }

    override fun getItemCount(): Int {

        return 2
    }
}