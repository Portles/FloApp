package com.h5190001.flo.ui.categories

import com.h5190001.flo.data.locale.CategoryDataSource
import com.h5190001.flo.data.models.CategoryResponse
import com.h5190001.flo.data.remote.CategoryService
import com.h5190001.flo.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteCategoryDataSource : CategoryDataSource {

    override  fun getAllCategorys(): Flow<Resource<CategoryResponse>> = flow {
        try {

            emit(Resource.Loading())

            val response = CategoryService.build().getAllCategorys()

            if (response.isSuccessful) {

                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }

        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}