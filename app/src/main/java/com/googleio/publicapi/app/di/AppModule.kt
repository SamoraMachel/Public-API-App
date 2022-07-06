package com.googleio.publicapi.app.di

import com.googleio.publicapi.data.remote.api.PublicApi
import com.googleio.publicapi.data.remote.repository.CategoryRepositoryImpl
import com.googleio.publicapi.data.remote.repository.EntryRepositoryImpl
import com.googleio.publicapi.domain.repository.CategoryRepository
import com.googleio.publicapi.domain.repository.EntryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
      @Provides
      @Singleton
      fun providesCategoryRepository(publicApi: PublicApi) : CategoryRepository {
        return CategoryRepositoryImpl(publicApi)
      }

     @Provides
     @Singleton
     fun providesEntryRepository(publicApi: PublicApi) : EntryRepository {
       return EntryRepositoryImpl(publicApi)
     }


}