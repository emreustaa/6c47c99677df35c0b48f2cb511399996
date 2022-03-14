package com.emreusta.spacechallenge.data.repository

import com.emreusta.spacechallenge.data.model.response.SpaceResponseModel
import com.emreusta.spacechallenge.data.service.RetrofitApi
import com.emreusta.spacechallenge.utils.di.IoDispatcher
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
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SpaceRepositoryInterface {

    override fun addFavoriteStation(station: SpaceResponseModel) {

    }

    override fun deleteFavoriteStation(station: SpaceResponseModel) {

    }

    override fun getFavoriteStations(): SpaceResponseModel {
        return SpaceResponseModel()
    }

    override fun getAllSpaceDate(): Flow<Response<SpaceResponseModel>> {
        return flow {
            emit(retrofitApi.getAllData())
        }.flowOn(ioDispatcher)
    }
}