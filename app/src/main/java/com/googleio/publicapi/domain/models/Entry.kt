package com.googleio.publicapi.domain.models

data class Entry(
    val API: String,
    val Category: String,
    val Description: String,
    val HTTPS: Boolean,
    val Link: String
)