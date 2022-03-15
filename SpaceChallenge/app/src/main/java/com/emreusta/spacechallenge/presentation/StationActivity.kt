package com.emreusta.spacechallenge.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.emreusta.spacechallenge.R
import com.emreusta.spacechallenge.databinding.ActivityStationBinding
import com.emreusta.spacechallenge.presentation.viewmodel.StationsViewModel
import com.emreusta.spacechallenge.utils.Constants.CAPACITY
import com.emreusta.spacechallenge.utils.Constants.DURABILITY
import com.emreusta.spacechallenge.utils.Constants.NAME
import com.emreusta.spacechallenge.utils.Constants.SPEED
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStationBinding

    private val spaceViewModel: StationsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_controller)

        with(binding) {
            bottomBarStation.setupWithNavController(navController)
        }
        spaceViewModel.getAllStations()
    }

    companion object {
        fun callingIntent(
            context: Context,
            name: String,
            durability: Int,
            speed: Int,
            capacity: Int
        ): Intent =
            Intent(context, StationActivity::class.java).apply {
                putExtra(NAME, name)
                putExtra(DURABILITY, durability)
                putExtra(SPEED, speed)
                putExtra(CAPACITY, capacity)
            }
    }
}