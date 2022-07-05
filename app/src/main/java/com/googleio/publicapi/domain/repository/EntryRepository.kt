package com.googleio.publicapi.domain.repository

import com.googleio.publicapi.domain.models.BaseEntry
import com.googleio.publicapi.domain.models.Resource

interface EntryRepository {
    fun getEntries() : Resource<BaseEntry>
}