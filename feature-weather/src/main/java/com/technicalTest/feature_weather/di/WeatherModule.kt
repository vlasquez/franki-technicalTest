package com.technicalTest.feature_weather.di

import com.technicalTest.feature_weather.data.CityWeatherRepositoryImpl
import com.technicalTest.feature_weather.data.WeatherDataSource
import com.technicalTest.feature_weather.data.remote.WeatherApi
import com.technicalTest.feature_weather.data.remote.WeatherRemoteDataSource
import com.technicalTest.feature_weather.data_access.CityWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class WeatherModule {

    @Binds
    abstract fun providesWeatherRepository(
        repository: CityWeatherRepositoryImpl,
    ): CityWeatherRepository


    @Binds
    abstract fun providesWeatherDataSource(
        dataSource: WeatherRemoteDataSource,
    ): WeatherDataSource

    companion object {

        @Singleton
        @Provides
        fun providesWeatherApi(
           retrofit: Retrofit
        ): WeatherApi = retrofit.create(WeatherApi::class.java)
    }
}