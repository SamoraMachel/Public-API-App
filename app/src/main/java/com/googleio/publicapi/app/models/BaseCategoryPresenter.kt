package com.googleio.publicapi.app.models


data class BaseCategoryPresenter(
    val count: Int,
    val categories: List<CategoryPresenter>
)
