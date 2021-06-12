package com.h5190001.flo.data.datasource

import com.h5190001.flo.data.models.UserResponse
import com.h5190001.flo.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun getAllUsers(): Flow<Resource<UserResponse>>
}