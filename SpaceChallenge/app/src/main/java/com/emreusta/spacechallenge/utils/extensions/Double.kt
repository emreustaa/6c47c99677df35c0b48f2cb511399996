package com.emreusta.spacechallenge.utils.extensions

fun Double?.doubleOrZero(): Double {
    return this ?: 0.0
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)