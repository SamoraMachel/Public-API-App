package com.googleio.publicapi.data.remote.api

import retrofit2.http.GET

interface PublicApi {

    @GET("/entries")
    fun fetchEntries()

    @GET("/categories")
    fun fetchCategories()
}