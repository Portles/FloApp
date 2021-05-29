package com.h5190001.flo.data.locale

import com.h5190001.flo.models.CategoryResponse
import com.h5190001.flo.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {
    fun getAllCategorys(): Flow<Resource<CategoryResponse>>
}