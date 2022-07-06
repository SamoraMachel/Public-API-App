package com.googleio.publicapi.domain.usecases

import com.googleio.publicapi.domain.models.BaseEntry
import com.googleio.publicapi.domain.models.Resource
import com.googleio.publicapi.domain.repository.EntryRepository
import javax.inject.Inject

class FetchEntryUseCase @Inject constructor(
    private val entryRepository: EntryRepository
) {
    suspend operator fun invoke(): Resource<BaseEntry> {
        return entryRepository.getEntries()
    }
}