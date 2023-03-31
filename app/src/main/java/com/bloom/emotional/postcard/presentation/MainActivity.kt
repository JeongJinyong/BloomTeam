package com.bloom.emotional.postcard.presentation

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.bloom.emotional.postcard.R
import com.bloom.emotional.postcard.base.BaseActivity
import com.bloom.emotional.postcard.databinding.ActivityMainBinding
import com.bloom.emotional.postcard.getCurrentTime
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.gun0912.tedpermission.coroutine.TedPermission
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
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            lifecycleScope.launch {
                TedPermission.create()
                    .setPermissions(Manifest.permission.POST_NOTIFICATIONS)
                    .check()
            }
        }

        val fragmentManager = supportFragmentManager
        val storiesFragment = StoriesFragment()
        val historyFragment = HistoryFragment()
        fragmentManager.beginTransaction().replace(R.id.ll_fragment, storiesFragment).commitAllowingStateLoss()
        binding.imgStories.isSelected = true
        binding.imgStories.setOnClickListener {
            binding.imgStories.isSelected = true
            binding.imgHistory.isSelected = false
            fragmentManager.beginTransaction().replace(R.id.ll_fragment, storiesFragment).commitAllowingStateLoss()
        }
        binding.imgHistory.setOnClickListener {
            binding.imgStories.isSelected = false
            binding.imgHistory.isSelected = true
            fragmentManager.beginTransaction().replace(R.id.ll_fragment, historyFragment).commitAllowingStateLoss()
        }
        binding.imgShare.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                // 원본 원격 이미지 URL
                val url = "http://118.67.135.198:8000/bloom/content?date=${getCurrentTime()}&img_ext=JPG"

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
                    viewModel.saveLinks(url)
                    viewModel.setShare()
                }
            }
        }
        firebaseMessaging()
    }

    private fun firebaseMessaging() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            viewModel.setPush(token)
        })
    }

}