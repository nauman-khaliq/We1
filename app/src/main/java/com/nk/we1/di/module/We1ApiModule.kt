/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 *
 */

package com.nk.we1.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.nk.we1.data.remote.api.We1Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * [We1ApiModule] that will handle api related dependencies.
 * [We1AppModule] is included in this module
 */
@InstallIn(SingletonComponent::class)
@Module(includes = [We1AppModule::class])
class We1ApiModule {

    /**
     * Provides retrofit service with Moshi converter and okhttp client
     * @param okHttpClient of type [OkHttpClient]
     * @return [We1Service]
     */
    @Singleton
    @Provides
    fun provideRetrofitService(okHttpClient: OkHttpClient): We1Service = Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .baseUrl(We1Service.We1_API_URL)
        .client(okHttpClient)
        .build()
        .create(We1Service::class.java)
}
