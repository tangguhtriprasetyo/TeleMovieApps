package com.example.dicodingmovieapps.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.dicodingmovieapps.R
import com.example.dicodingmovieapps.databinding.ActivityFavoriteBinding
import com.example.dicodingmovieapps.ui.home.HomeActivity
import com.example.dicodingmovieapps.ui.home.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private var doubleBackToExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavFavorite.selectedItemId = R.id.favorite

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPagerFavorite
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabsFavorite

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tv_title_movies)
                1 -> tab.text = getString(R.string.tv_title_series)
            }
        }.attach()

        binding.bottomNavFavorite.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    setActivity(true)
                }
                R.id.favorite -> {
                    setActivity(false)
                }
            }
            true
        }
    }

    private fun setActivity(isHome: Boolean) {
        if (isHome) {
            val intentHome = Intent(this@FavoriteActivity, HomeActivity::class.java)
            startActivity(intentHome)
            overridePendingTransition(0, 0)
            finish()
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExit) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExit = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExit = false }, 2000)
    }
}