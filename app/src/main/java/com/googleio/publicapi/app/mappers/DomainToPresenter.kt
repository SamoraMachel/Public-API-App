package com.googleio.publicapi.app.mappers

import com.googleio.publicapi.app.models.BaseCategoryPresenter
import com.googleio.publicapi.app.models.BaseEntryPresenter
import com.googleio.publicapi.app.models.CategoryPresenter
import com.googleio.publicapi.app.models.EntryPresenter
import com.googleio.publicapi.domain.models.BaseCategory
import com.googleio.publicapi.domain.models.BaseEntry
import com.googleio.publicapi.domain.models.Category
import com.googleio.publicapi.domain.models.Entry

fun Entry.toPresenter() = EntryPresenter(
    API,
    Category,
    Description,
    HTTPS,
    Link
)

fun Category.toPresenter() = CategoryPresenter(
    category
)

fun BaseEntry.toPresenter() = BaseEntryPresenter(
    count,
    entries.map {
        it.toPresenter()
    }
)

fun BaseCategory.toPresenter() = BaseCategoryPresenter(
    count,
    categories.map {
        it.toPresenter()
    }
)