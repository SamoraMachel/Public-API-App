package com.googleio.publicapi.data.remote.models

data class BaseEntryDto(
    val count: Int,
    val entries: List<EntryDto>
)