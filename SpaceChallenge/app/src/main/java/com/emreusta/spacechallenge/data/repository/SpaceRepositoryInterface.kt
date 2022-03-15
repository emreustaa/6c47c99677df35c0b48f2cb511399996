package com.emreusta.spacechallenge.data.repository

import com.emreusta.spacechallenge.data.model.response.SpaceResponseModel
import com.emreusta.spacechallenge.data.model.room.FavoriteStationModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface SpaceRepositoryInterface {

    suspend fun addFavoriteStation(station: FavoriteStationModel)

    suspend fun deleteFavoriteStation(station: FavoriteStationModel)

    suspend fun getFavoriteStations(): Flow<List<FavoriteStationModel>>

    fun isFavoriteStation(name: String): FavoriteStationModel?

    fun getAllSpaceDate(): Flow<Response<SpaceResponseModel>>

}