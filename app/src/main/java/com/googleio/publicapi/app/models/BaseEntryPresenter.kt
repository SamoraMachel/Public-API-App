package com.googleio.publicapi.app.models

data class BaseEntryPresenter(
    val count: Int,
    val entries: List<EntryPresenter>
)