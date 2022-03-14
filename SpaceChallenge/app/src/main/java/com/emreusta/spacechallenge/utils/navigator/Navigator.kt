package com.emreusta.spacechallenge.utils.navigator

import android.content.Context
import com.emreusta.spacechallenge.presentation.StationActivity

object Navigator {

    fun navigateToStation(context: Context, name: String) {
        context.startActivity(StationActivity.callingIntent(context, name))

    }
}