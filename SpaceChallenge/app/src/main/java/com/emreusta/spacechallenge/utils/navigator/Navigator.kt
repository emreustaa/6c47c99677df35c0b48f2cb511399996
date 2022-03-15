package com.emreusta.spacechallenge.utils.navigator

import android.content.Context
import com.emreusta.spacechallenge.presentation.StationActivity

object Navigator {

    fun navigateToStation(
        context: Context,
        name: String,
        durability: Int,
        speed: Int,
        capacity: Int
    ) {
        context.startActivity(
            StationActivity.callingIntent(
                context = context,
                name = name,
                durability = durability,
                speed = speed,
                capacity = capacity
            )
        )
    }
}