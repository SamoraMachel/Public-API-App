package com.googleio.publicapi.app.di

import com.googleio.publicapi.data.remote.api.PublicApi
import com.googleio.publicapi.data.remote.api.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofitBuilder() = RetrofitBuilder()


    @Provides
    @Singleton
    fun providesPublicAPI(retrofitBuilder: RetrofitBuilder) : PublicApi{
        return retrofitBuilder.build(PublicApi::class.java)
    }
}