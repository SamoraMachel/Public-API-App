package com.googleio.publicapi.data.remote.api

import com.googleio.publicapi.data.remote.models.BaseCategoryDto
import com.googleio.publicapi.data.remote.models.BaseEntryDto
import retrofit2.Response
import retrofit2.http.GET

interface PublicApi {

    @GET("/entries")
    fun fetchEntries() : Response<BaseEntryDto>

    @GET("/categories")
    fun fetchCategories() : Response<BaseCategoryDto>
}