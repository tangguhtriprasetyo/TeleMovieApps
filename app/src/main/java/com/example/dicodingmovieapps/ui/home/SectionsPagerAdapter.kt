package com.example.dicodingmovieapps.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dicodingmovieapps.ui.home.movies.MoviesFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {

        return MoviesFragment.newInstance(position + 1)
    }

    override fun getItemCount(): Int {

        return 2
    }
}