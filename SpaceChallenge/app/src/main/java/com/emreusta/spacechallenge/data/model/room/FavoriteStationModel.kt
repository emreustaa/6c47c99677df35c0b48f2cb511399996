package com.emreusta.spacechallenge.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteStations")
data class FavoriteStationModel(
    val name: String,
    val distance: String,
    val capacity: Int,
    @PrimaryKey(autoGenerate = true) var id: Int? = null
)
