package com.example.codechallengestorage.GameView

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.codechallengestorage.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler()

        handler.postDelayed({
            startActivity(Intent(this, LandingPageActivity::class.java))
            finish()
        }, 1000)
    }
}