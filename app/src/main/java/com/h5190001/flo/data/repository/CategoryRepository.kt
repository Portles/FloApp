package com.h5190001.flo.data.repository

import com.h5190001.flo.ui.categories.RemoteCategoryDataSource
import com.h5190001.flo.data.locale.CategoryDataSource
import com.h5190001.flo.data.models.CategoryResponse
import com.h5190001.flo.utils.Resource
import kotlinx.coroutines.flow.Flow

class CategoryRepository {
    private var categoryDataSource: CategoryDataSource?=null

    init {
        categoryDataSource= RemoteCategoryDataSource()
    }

    fun getAllCategorys(): Flow<Resource<CategoryResponse>>
    {
        return categoryDataSource!!.getAllCategorys()
    }
}