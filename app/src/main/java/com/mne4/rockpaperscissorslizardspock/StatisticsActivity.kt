package com.mne4.rockpaperscissorslizardspock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.mne4.rockpaperscissorslizardspock.databinding.ActivityStatisticsBinding
import java.util.*

class StatisticsActivity : AppCompatActivity() {
    lateinit var binding: ActivityStatisticsBinding
    val date = Calendar.getInstance().time
    lateinit var startTime: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startTime = intent.getStringExtra("Time").toString()
        timeDifference()
        binding.textViewLoseCount.text = intent.getStringExtra("Lose")
        binding.textViewWinCount.text = intent.getStringExtra("Win")
        binding.textViewDrawCount.text = intent.getStringExtra("Draw")


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Статистика"
    }

    fun timeDifference() {
        val start = startTime?.split(":")
        val now = "${date.hours}:${date.minutes}:${date.seconds}".split(":")

        if (start != null) {
            var hours = (now[0].toInt() - start[0].toInt() + 24) % 24
            var minutes = (now[1].toInt() - start[1].toInt() + 60) % 60
            val seconds = (now[2].toInt() - start[2].toInt() + 60) % 60
            if (now[2].toInt() < start[2].toInt()) {
                minutes -= 1
            }
            if (now[1].toInt() < start[1].toInt()) {
                hours -= 1
            }
            binding.textViewTimeCount.text = "$hours часов $minutes минут $seconds секунд"
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }
}