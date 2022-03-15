package com.emreusta.spacechallenge.utils.extensions

import kotlin.math.sqrt

object DistanceCalculator {
    fun calculateDistance(
        currentX: Double,
        currentY: Double,
        nextX: Double,
        nextY: Double
    ): Double {
        val a = currentX - nextX
        val b = currentY - nextY
        return sqrt(a * a + b * b)
    }
}