package com.googleio.publicapi.domain.repository

import com.googleio.publicapi.domain.models.BaseCategory
import com.googleio.publicapi.domain.models.Resource

interface CategoryRepository {
    fun getCategories() : Resource<BaseCategory>
}