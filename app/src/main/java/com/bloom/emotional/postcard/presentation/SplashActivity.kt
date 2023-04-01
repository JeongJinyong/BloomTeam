package com.bloom.emotional.postcard.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bloom.emotional.postcard.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch {
            delay(1000)
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            finish()
        }
    }

}