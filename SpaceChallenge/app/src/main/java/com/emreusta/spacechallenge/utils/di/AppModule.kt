package com.emreusta.spacechallenge.utils.di

import android.content.Context
import androidx.room.Room
import com.emreusta.spacechallenge.data.repository.SpaceRepository
import com.emreusta.spacechallenge.data.service.RetrofitApi
import com.emreusta.spacechallenge.utils.Constants
import com.emreusta.spacechallenge.utils.room.StationDao
import com.emreusta.spacechallenge.utils.room.StationDatabase
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, StationDatabase::class.java, "StationDB").allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun injectDao(database: StationDatabase) = database.stationDao()

    private var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
    private var gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun injectRetrofitAPI(): RetrofitApi {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).client(
            client
        ).baseUrl(Constants.BASE_API).build().create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSpaceRepository(
        retrofitApi: RetrofitApi,
        stationDao: StationDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ) = SpaceRepository(retrofitApi, stationDao, ioDispatcher)

    @IoDispatcher
    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher
