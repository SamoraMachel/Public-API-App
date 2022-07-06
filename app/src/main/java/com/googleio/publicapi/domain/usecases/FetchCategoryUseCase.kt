package com.googleio.publicapi.domain.usecases

import com.googleio.publicapi.domain.models.BaseCategory
import com.googleio.publicapi.domain.models.Resource
import com.googleio.publicapi.domain.repository.CategoryRepository
import javax.inject.Inject

class FetchCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
){
    suspend operator fun invoke(): Resource<BaseCategory> {
        return categoryRepository.getCategories()
    }
}