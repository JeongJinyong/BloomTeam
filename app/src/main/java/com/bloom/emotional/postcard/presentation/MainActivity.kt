package com.bloom.emotional.postcard.presentation

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.content.FileProvider
import com.bloom.emotional.postcard.R
import com.bloom.emotional.postcard.base.BaseActivity
import com.bloom.emotional.postcard.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            // 원본 원격 이미지 URL
            val url = "https://t1.kakaocdn.net/kakaocorp/Service/KakaoTalk/pc/slide/talkpc_theme_01.jpg"

            // Download the image using a background thread (e.g. using an AsyncTask or Kotlin coroutines)
            val bitmap = try {
                URL(url).openStream().use { stream ->
                    BitmapFactory.decodeStream(stream)
                }
            } catch (e: IOException) {
                // Handle error
                null
            }

            if (bitmap != null) {
                // Save the bitmap to a file in the app's cache directory
                val file = File(cacheDir, "shared_image.png")
                FileOutputStream(file).use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                }

                // Share the file using an intent
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "image/png"
                    putExtra(
                        Intent.EXTRA_STREAM,
                        FileProvider.getUriForFile(this@MainActivity, this@MainActivity.applicationContext.packageName + ".provider", file)
                    )
                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                }
                startActivity(Intent.createChooser(shareIntent, "Share image using"))
            }
        }
    }
}