package com.googleio.publicapi.data.remote.models

data class BaseCategoryDto(
    val count: Int,
    val categories: List<CategoryDto>
)