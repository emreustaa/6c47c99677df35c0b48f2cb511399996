package com.emreusta.spacechallenge.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emreusta.spacechallenge.databinding.ActivityMainBinding
import com.emreusta.spacechallenge.utils.navigator.Navigator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnContinue.setOnClickListener {
                Navigator.navigateToStation(this@MainActivity, etName.text.toString())
                etName.setText("")
            }
        }
    }
}