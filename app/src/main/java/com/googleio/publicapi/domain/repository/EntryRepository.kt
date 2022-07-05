package com.googleio.publicapi.domain.repository

import com.googleio.publicapi.domain.models.BaseEntry
import com.googleio.publicapi.domain.models.Resource


interface EntryRepository {
    suspend fun getEntries() :  Resource<BaseEntry>
}