package com.googleio.publicapi.data.remote.mappers

import com.googleio.publicapi.data.remote.models.BaseCategoryDto
import com.googleio.publicapi.data.remote.models.BaseEntryDto
import com.googleio.publicapi.data.remote.models.CategoryDto
import com.googleio.publicapi.data.remote.models.EntryDto
import com.googleio.publicapi.domain.models.BaseCategory
import com.googleio.publicapi.domain.models.BaseEntry
import com.googleio.publicapi.domain.models.Category
import com.googleio.publicapi.domain.models.Entry

fun CategoryDto.toDomain() : Category = Category(
    category
)

fun EntryDto.toDomain() : Entry = Entry(
    API,
    Category,
    Description,
    HTTPS,
    Link
)

fun BaseEntryDto.toDomain() : BaseEntry = BaseEntry(
    count,
    entries.map {
        it.toDomain()
    }
)

fun BaseCategoryDto.toDomain() : BaseCategory = BaseCategory(
    count,
    categories.map {
        it.toDomain()
    }
)