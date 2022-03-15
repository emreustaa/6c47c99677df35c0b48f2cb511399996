package com.emreusta.spacechallenge.data.service

import com.emreusta.spacechallenge.data.model.response.SpaceResponseModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/v3/e7211664-cbb6-4357-9c9d-f12bf8bab2e2")
    suspend fun getAllData(): Response<SpaceResponseModel>
}