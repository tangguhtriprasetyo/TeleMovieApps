package com.example.dicodingmovieapps.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.dicodingmovieapps.databinding.ActivitySplashScreenBinding
import com.example.dicodingmovieapps.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    private val timeOut: Long = 2000

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, timeOut)
    }
}