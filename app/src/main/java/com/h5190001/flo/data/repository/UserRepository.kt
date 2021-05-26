package com.h5190001.flo.data.repository

import com.h5190001.flo.data.locale.UserDataSource
import com.h5190001.flo.data.datasource.remote.RemoteUserDataSource
import com.h5190001.flo.models.UserResponse
import com.h5190001.flo.utils.Resource
import kotlinx.coroutines.flow.Flow

class UserRepository {
    private var userDataSource: UserDataSource?=null

    init {
        userDataSource= RemoteUserDataSource()
    }

    fun getAllUsers(): Flow<Resource<UserResponse>>
    {
        return userDataSource!!.getAllUsers()
    }
}