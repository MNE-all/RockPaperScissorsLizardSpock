package com.mne4.rockpaperscissorslizardspock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.mne4.rockpaperscissorslizardspock.databinding.ActivityMainBinding
import java.util.Calendar
import java.util.Date
import kotlin.time.Duration.Companion.hours

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var launcher: ActivityResultLauncher<Intent>? = null
    var winCount = 0
    var loseCount = 0
    var drawCount = 0
    lateinit var date: Date


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.title = "Главная"

        date = Calendar.getInstance().time




        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                when (result.data?.getStringExtra("result")) {
                    "Win" -> {
                        winCount += 1
                    }
                    "Lose" -> {
                        loseCount += 1
                    }
                    "Draw" -> {
                        drawCount += 1
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top, menu)

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuItemStatistics -> {
                val statisticIntent = Intent(this, StatisticsActivity::class.java)
                statisticIntent.putExtra("Win", winCount.toString())
                statisticIntent.putExtra("Lose", loseCount.toString())
                statisticIntent.putExtra("Draw", drawCount.toString())
                statisticIntent.putExtra("Time", "${date.hours}:${date.minutes}:${date.seconds}")
                startActivity(statisticIntent)
            }
            R.id.menuItemRules -> {
                startActivity(Intent(this, RulesActivity::class.java))
            }
        }
        return true
    }


    fun buttonClick(view: View) {
        val resultIntent = Intent(this, ResultActivity::class.java)
        when (view.id){
            binding.buttonRock.id -> {
                resultIntent.putExtra("userAnswer", binding.buttonRock.text)
            }
            binding.buttonPaper.id -> {
                resultIntent.putExtra("userAnswer", binding.buttonPaper.text)
            }
            binding.buttonScissors.id -> {
                resultIntent.putExtra("userAnswer", binding.buttonScissors.text)
            }
            binding.buttonLizard.id -> {
                resultIntent.putExtra("userAnswer", binding.buttonLizard.text)
            }
            binding.buttonSpock.id -> {
                resultIntent.putExtra("userAnswer", binding.buttonSpock.text)
            }
        }
        launcher?.launch(resultIntent)
    }
}