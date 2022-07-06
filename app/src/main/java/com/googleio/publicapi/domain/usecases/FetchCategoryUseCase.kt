package com.googleio.publicapi.domain.usecases

import com.googleio.publicapi.domain.models.BaseCategory
import com.googleio.publicapi.domain.models.Resource
import com.googleio.publicapi.domain.repository.CategoryRepository

class FetchCategoryUseCase constructor(
    private val categoryRepository: CategoryRepository
){
    suspend operator fun invoke(): Resource<BaseCategory> {
        return categoryRepository.getCategories()
    }
}