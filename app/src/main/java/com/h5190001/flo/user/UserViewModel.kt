package com.h5190001.flo.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190001.flo.data.repository.UserRepository
import com.h5190001.flo.models.UserResponse
import com.h5190001.flo.utils.ResourceStatus
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private  val userRepository: UserRepository =UserRepository()

    init {
        getAllUsers()
    }

    var allUsersLiveData : MutableLiveData<UserResponse>? = null
    var error :    MutableLiveData<Throwable>? = null
    var loading :    MutableLiveData<Boolean>? = null

    fun getAllUsers()  = viewModelScope.launch {

        userRepository.getAllUsers()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        allUsersLiveData?.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        Log.e("ERROR", "${it.throwable}")
                        error?.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}