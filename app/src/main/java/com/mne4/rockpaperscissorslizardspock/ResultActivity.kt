package com.mne4.rockpaperscissorslizardspock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.mne4.rockpaperscissorslizardspock.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var options: Array<String>
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Итог сражения"
        options = arrayOf(getString(R.string.rock), getString(R.string.paper), getString(R.string.scissors), getString(R.string.lizard), getString(R.string.spock))
        val userChoice = intent.getStringExtra("userAnswer")
        if (!userChoice.isNullOrEmpty()) {
            getResult(userChoice)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            goBack(View(this))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getResult(choice: String) {
        val computerChoice = options.random()

        if (computerChoice == choice) {
            binding.textViewResult.text = getString(R.string.draw)
            binding.imageView.setImageDrawable(getDrawable(R.drawable.draw))
        }

        when (choice) {
            getString(R.string.rock) -> {
                when (computerChoice) {
                    getString(R.string.scissors), getString(R.string.lizard) -> {
                        binding.textViewResult.text = getString(R.string.win)
                    }
                    getString(R.string.spock), getString(R.string.paper) -> {
                        binding.textViewResult.text = getString(R.string.lose)
                    }
                }
            }
            getString(R.string.paper) -> {
                when (computerChoice) {
                    getString(R.string.rock), getString(R.string.spock) -> {
                        binding.textViewResult.text = getString(R.string.win)
                    }
                    getString(R.string.lizard), getString(R.string.scissors) -> {
                        binding.textViewResult.text = getString(R.string.lose)
                    }
                }
            }
            getString(R.string.scissors) -> {
                when (computerChoice) {
                    getString(R.string.paper), getString(R.string.lizard) -> {
                        binding.textViewResult.text = getString(R.string.win)
                    }
                    getString(R.string.spock), getString(R.string.rock) -> {
                        binding.textViewResult.text = getString(R.string.lose)
                    }
                }
            }
            getString(R.string.lizard) -> {
                when (computerChoice) {
                    getString(R.string.spock), getString(R.string.paper) -> {
                        binding.textViewResult.text = getString(R.string.win)
                    }
                    getString(R.string.scissors), getString(R.string.rock) -> {
                        binding.textViewResult.text = getString(R.string.lose)
                    }
                }
            }
            getString(R.string.spock) -> {
                when (computerChoice) {
                    getString(R.string.rock), getString(R.string.scissors) -> {
                        binding.textViewResult.text = getString(R.string.win)
                    }
                    getString(R.string.lizard), getString(R.string.paper) -> {
                        binding.textViewResult.text = getString(R.string.lose)
                    }
                }
            }
        }

        if (computerChoice == getString(R.string.rock) && choice == getString(R.string.scissors) || choice == getString(R.string.rock) && computerChoice == getString(R.string.scissors)) {
            binding.imageView.setImageDrawable(getDrawable(R.drawable.rock_scissors))
        }
        else if (computerChoice == getString(R.string.rock) && choice == getString(R.string.paper) || choice == getString(R.string.rock) && computerChoice == getString(R.string.paper)) {
            binding.imageView.setImageDrawable(getDrawable(R.drawable.paper_rock))
        }
        else if (computerChoice == getString(R.string.rock) && choice == getString(R.string.lizard) || choice == getString(R.string.rock) && computerChoice == getString(R.string.lizard)) {
            binding.imageView.setImageDrawable(getDrawable(R.drawable.rock_lizard))
        }
        else if (computerChoice == getString(R.string.rock) && choice == getString(R.string.spock) || choice == getString(R.string.rock) && computerChoice == getString(R.string.spock)) {
            binding.imageView.setImageDrawable(getDrawable(R.drawable.spock_rock))
        }
        else if (computerChoice == getString(R.string.lizard) && choice == getString(R.string.scissors) || choice == getString(R.string.lizard) && computerChoice == getString(R.string.scissors)) {
            binding.imageView.setImageDrawable(getDrawable(R.drawable.scissors_lizard))
        }
        else if (computerChoice == getString(R.string.paper) && choice == getString(R.string.scissors) || choice == getString(R.string.paper) && computerChoice == getString(R.string.scissors)) {
            binding.imageView.setImageDrawable(getDrawable(R.drawable.scissor_paper))
        }
        else if (computerChoice == getString(R.string.lizard) && choice == getString(R.string.spock) || choice == getString(R.string.lizard) && computerChoice == getString(R.string.spock)) {
            binding.imageView.setImageDrawable(getDrawable(R.drawable.lizard_spock))
        }
        else if (computerChoice == getString(R.string.spock) && choice == getString(R.string.scissors) || choice == getString(R.string.spock) && computerChoice == getString(R.string.scissors)) {
            binding.imageView.setImageDrawable(getDrawable(R.drawable.spock_scissors))
        }
        else if (computerChoice == getString(R.string.spock) && choice == getString(R.string.paper) || choice == getString(R.string.spock) && computerChoice == getString(R.string.paper)) {
            binding.imageView.setImageDrawable(getDrawable(R.drawable.paper_spock))
        }
        else if (computerChoice == getString(R.string.lizard) && choice == getString(R.string.paper) || choice == getString(R.string.lizard) && computerChoice == getString(R.string.paper)) {
            binding.imageView.setImageDrawable(getDrawable(R.drawable.lizard_paper))
        }
    }

    fun goBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        when (binding.textViewResult.text) {
            getString(R.string.win) -> {
                intent.putExtra("result", "Win")
            }
            getString(R.string.lose) -> {
                intent.putExtra("result", "Lose")
            }
            getString(R.string.draw) -> {
                intent.putExtra("result", "Draw")
            }
        }
        setResult(RESULT_OK, intent)

        finish()
    }
}