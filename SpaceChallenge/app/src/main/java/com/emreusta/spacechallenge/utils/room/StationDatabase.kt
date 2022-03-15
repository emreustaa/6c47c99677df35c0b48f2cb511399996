package com.emreusta.spacechallenge.utils.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emreusta.spacechallenge.data.model.room.FavoriteStationModel

@Database(entities = [FavoriteStationModel::class], version = 1)
abstract class StationDatabase : RoomDatabase() {

    abstract fun stationDao(): StationDao
}