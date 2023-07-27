package com.umutcansahin.recyclerviewautoscroll

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umutcansahin.recyclerviewautoscroll.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val images = listOf(
            Image("agriculture", R.drawable.baseline_agriculture_24),
            Image("airport_shuttle", R.drawable.baseline_airport_shuttle_24),
            Image("anchor", R.drawable.baseline_anchor_24),
            Image("architecture", R.drawable.baseline_architecture_24),
            Image("assured_workload", R.drawable.baseline_assured_workload_24),
            Image("attach_file", R.drawable.baseline_attach_file_24),
            Image("attractions", R.drawable.baseline_attractions_24),
            Image("bedroom_baby", R.drawable.baseline_bedroom_baby_24),
            Image("brightness_medium", R.drawable.baseline_brightness_medium_24),
        )

        vol1(images)
        //vol2(images)

    }

    private fun vol1(images: List<Image>) {
        adapter = ImageAdapter(images = images)

            // recyclerview 9sn boyunca hareket eder süre dolunca geriye döner
        val timer = object : CountDownTimer(9_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.recyclerview.post {
                    binding.recyclerview.smoothScrollToPosition(adapter.itemCount - 1)
                }
            }

            override fun onFinish() {
                binding.recyclerview.post {
                    binding.recyclerview.smoothScrollToPosition(0)
                }
            }
        }
            //recyclerview harekete baslamadan önce 2sn bekler
        object : CountDownTimer(2_000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                timer.start()
            }
        }.start()

        binding.recyclerview.layoutManager = RecyclerviewAutoScrollManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerview.adapter = adapter
    }

    private fun way2(images: List<Image>) {
        val layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        adapter = ImageAdapter(images)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter

        val handler = Handler(Looper.getMainLooper())

        val runnable = object : Runnable {
            override fun run() {
                val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()
                val itemCount = adapter.itemCount

                val conditionFirst = lastVisiblePosition != RecyclerView.NO_POSITION
                val conditionSecond = lastVisiblePosition == itemCount - 1
                if (conditionFirst && conditionSecond) {
                    layoutManager.scrollToPosition(0)
                } else {
                    binding.recyclerview.smoothScrollBy(50, 0)
                }
                handler.postDelayed(this, 100)
            }
        }
        handler.postDelayed(runnable, 100)
    }
}