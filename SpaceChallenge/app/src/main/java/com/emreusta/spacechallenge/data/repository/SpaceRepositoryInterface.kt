package com.emreusta.spacechallenge.data.repository

import com.emreusta.spacechallenge.data.model.response.SpaceResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface SpaceRepositoryInterface {

    fun addFavoriteStation(station: SpaceResponseModel)

    fun deleteFavoriteStation(station: SpaceResponseModel)

    fun getFavoriteStations(): SpaceResponseModel

    fun getAllSpaceDate(): Flow<Response<SpaceResponseModel>>

}