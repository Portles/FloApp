package com.h5190001.flo.ui.login

import com.h5190001.flo.data.locale.UserDataSource
import com.h5190001.flo.models.UserResponse
import com.h5190001.flo.services.UserService
import com.h5190001.flo.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteUserDataSource  : UserDataSource {

    override  fun getAllUsers(): Flow<Resource<UserResponse>> = flow {
        try {

            emit(Resource.Loading())

            val response = UserService.build().getAllUsers()

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