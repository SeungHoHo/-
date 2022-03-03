package com.example.hanbit.part2_chapter5

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hanbit.part2_chapter5.databinding.ActivityPhotoframeBinding
import java.util.*
import kotlin.concurrent.timer

class PhotoFrameActivity: AppCompatActivity() {

    private val photoList = mutableListOf<Uri>()

    private var currentPosition = 0

    private var timer: Timer? = null

    val binding by lazy { ActivityPhotoframeBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getPhotoUriFromItent()
    }

    private fun getPhotoUriFromItent() {
        val size = intent.getIntExtra("photoListSize", 0)
        for ( i in 0..size) {
            intent.getStringExtra("photo$i")?.let {
                photoList.add(Uri.parse(it))
            }
        }
    }

    private fun startTimer() {
        timer(period = 5 * 1000) {
            runOnUiThread {
                val current = currentPosition
                val next = if (photoList.size <= currentPosition + 1) 0 else currentPosition + 1

                binding.backgroundPhotoImageView.setImageURI(photoList[current])

                binding.photoImageView.alpha = 0f
                binding.photoImageView.setImageURI(photoList[next])
                binding.photoImageView.animate()
                    .alpha(1.0f)
                    .setDuration(1000)
                    .start()

                currentPosition = next
            }

        }

    }

    override fun onStop() {
        super.onStop()

        timer?.cancel()
    }

    override fun onStart() {
        super.onStart()

        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()

        timer?.cancel()
    }



}