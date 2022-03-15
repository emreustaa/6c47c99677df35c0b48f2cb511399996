package com.emreusta.spacechallenge.utils.extensions

fun Int?.orZero(): Int {
    return this ?: 0
}