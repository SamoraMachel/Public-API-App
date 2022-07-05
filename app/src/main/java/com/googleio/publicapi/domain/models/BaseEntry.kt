package com.googleio.publicapi.domain.models

data class BaseEntry(
    val count: Int,
    val entries: List<Entry>
)