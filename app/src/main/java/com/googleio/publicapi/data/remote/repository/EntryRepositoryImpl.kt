package com.googleio.publicapi.data.remote.repository

import com.googleio.publicapi.data.remote.api.PublicApi
import com.googleio.publicapi.data.remote.mappers.toDomain
import com.googleio.publicapi.domain.models.BaseEntry
import com.googleio.publicapi.domain.models.Resource
import com.googleio.publicapi.domain.repository.EntryRepository
import java.io.IOException

class EntryRepositoryImpl(
    private val publicApi: PublicApi
) : EntryRepository{
    override suspend fun getEntries(): Resource<BaseEntry> {
        return try {
            val returnData = publicApi.fetchEntries()
            if(returnData.isSuccessful)
                return Resource.Success(returnData.body()?.toDomain())
            else
                return Resource.Error("Server Error: Unknown Error Occurred", returnData.body()?.toDomain())
        } catch (error: IOException) {
            Resource.Error("Network Error: Kindly check your internet")
        } catch (error: Exception) {
                Resource.Error(error.message)
        }
    }
}