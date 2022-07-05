package com.googleio.publicapi.domain.usecases

import com.googleio.publicapi.domain.models.BaseEntry
import com.googleio.publicapi.domain.models.Resource
import com.googleio.publicapi.domain.repository.EntryRepository

class FetchEntryUseCase constructor(
    private val entryRepository: EntryRepository
) {
    suspend fun invoke(): Resource<BaseEntry> {
        return entryRepository.getEntries()
    }
}