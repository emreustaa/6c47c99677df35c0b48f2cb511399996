package com.emreusta.spacechallenge.utils.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.emreusta.spacechallenge.data.model.room.FavoriteStationModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteStation(model: FavoriteStationModel)

    @Delete
    suspend fun deleteFavoriteStation(model: FavoriteStationModel)

    @Query("SELECT * FROM favoriteStations")
    fun getFavoriteStations(): Flow<List<FavoriteStationModel>>

    @Query("SELECT * FROM favoriteStations WHERE name=:favoriteName")
    fun isFavorite(favoriteName: String): FavoriteStationModel?


}