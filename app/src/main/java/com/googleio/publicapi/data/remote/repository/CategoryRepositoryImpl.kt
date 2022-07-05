package com.googleio.publicapi.data.remote.repository

import com.googleio.publicapi.data.remote.api.PublicApi
import com.googleio.publicapi.data.remote.mappers.toDomain
import com.googleio.publicapi.domain.models.BaseCategory
import com.googleio.publicapi.domain.models.Resource
import com.googleio.publicapi.domain.repository.CategoryRepository
import java.io.IOException

class CategoryRepositoryImpl constructor(
    private val publicApi: PublicApi
) : CategoryRepository {
    override suspend fun getCategories(): Resource<BaseCategory> {
        return try {
            val returnData = publicApi.fetchCategories()
            if(returnData.isSuccessful)
                return Resource.Success(returnData.body()?.toDomain())
            else
                return Resource.Error("Unknown Error Occurred", returnData.body()?.toDomain())
        } catch (error: IOException) {
            Resource.Error("Network Error. Please check you Internet")
        } catch (error: Exception) {
            Resource.Error("Unknown Error Occurred")
        }
    }
}