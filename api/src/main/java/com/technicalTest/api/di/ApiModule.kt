package com.technicalTest.api.di

import com.technicalTest.api.ApiParam
import com.technicalTest.api.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = RetrofitFactory.createInstance(ApiParam)
}