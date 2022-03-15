package com.emreusta.spacechallenge.presentation

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.emreusta.spacechallenge.databinding.ActivityMainBinding
import com.emreusta.spacechallenge.utils.extensions.listener
import com.emreusta.spacechallenge.utils.navigator.Navigator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var durabilityValue = 0
    var speedValue = 0
    var capacityValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            sbDurability.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                    seekBar?.let {
                        tvDurabilityTitle.text = "Dayanıklılık : ${it.progress}"
                        durabilityValue = it.progress
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            sbCapacity.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                    seekBar?.let {
                        tvCapacityTitle.text = "Kapasite : ${it.progress}"
                        capacityValue = it.progress
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            sbSpeed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                    seekBar?.let {
                        tvSpeedTitle.text = "Hız : ${it.progress}"
                        speedValue = it.progress
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            btnContinue.setOnClickListener {

                val inputName = etName.text.toString()
                if (validateInputs(inputName, durabilityValue, speedValue, capacityValue)) {
                    Navigator.navigateToStation(
                        this@MainActivity,
                        name = inputName,
                        durability = durabilityValue,
                        speed = speedValue,
                        capacity = capacityValue
                    )
                    etName.setText("")
                    finish()
                }
            }
        }
    }

    fun validateInputs(
        name: String,
        durability: Int,
        speed: Int,
        capacity: Int
    ): Boolean {
        val total = durability + capacity + speed
        return if (name.isBlank() || durability == 0 || speed == 0 || capacity == 0) {
            Toast.makeText(this, "Values cannot be 0 or empty", Toast.LENGTH_SHORT).show()
            false
        } else if (total != 15) {
            Toast.makeText(this, "Total value must be 15", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }
}