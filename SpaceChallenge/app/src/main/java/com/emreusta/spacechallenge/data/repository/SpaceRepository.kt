package com.emreusta.spacechallenge.data.repository

import androidx.lifecycle.LiveData
import com.emreusta.spacechallenge.data.model.response.SpaceResponseModel
import com.emreusta.spacechallenge.data.model.room.FavoriteStationModel
import com.emreusta.spacechallenge.data.service.RetrofitApi
import com.emreusta.spacechallenge.utils.di.IoDispatcher
import com.emreusta.spacechallenge.utils.room.StationDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpaceRepository @Inject constructor(
    private val retrofitApi: RetrofitApi,
    private val stationDao: StationDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SpaceRepositoryInterface {
    override suspend fun addFavoriteStation(station: FavoriteStationModel) {
        stationDao.addFavoriteStation(station)
    }

    override suspend fun deleteFavoriteStation(station: FavoriteStationModel) {
        stationDao.deleteFavoriteStation(station)
    }

    override suspend fun getFavoriteStations(): Flow<List<FavoriteStationModel>> {
        return stationDao.getFavoriteStations()
    }

    override fun isFavoriteStation(name : String): FavoriteStationModel? {
        return stationDao.isFavorite(name)
    }

    override fun getAllSpaceDate(): Flow<Response<SpaceResponseModel>> {
        return flow {
            emit(retrofitApi.getAllData())
        }.flowOn(ioDispatcher)
    }
}