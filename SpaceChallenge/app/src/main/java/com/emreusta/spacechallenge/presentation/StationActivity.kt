package com.emreusta.spacechallenge.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.emreusta.spacechallenge.R
import com.emreusta.spacechallenge.databinding.ActivityStationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_controller)

        with(binding) {
            bottomBarStation.setupWithNavController(navController)
        }
    }

    companion object {
        const val NAME = "NAME"
        fun callingIntent(context: Context, name: String): Intent =
            Intent(context, StationActivity::class.java).apply {
                putExtra(NAME, name)
            }
    }
}