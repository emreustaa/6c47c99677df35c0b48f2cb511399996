package com.emreusta.spacechallenge.utils.extensions

import android.widget.SeekBar

fun SeekBar.listener(): Int {

    var progressValue = 0
    this.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
            seekBar?.let {
                progressValue = it.progress
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    })

    return progressValue
}