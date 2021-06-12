package com.h5190001.flo.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190001.flo.data.repository.UserRepository
import com.h5190001.flo.data.models.UserResponse
import com.h5190001.flo.utils.enums.ResourceStatusEnum
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private  val userRepository: UserRepository =UserRepository()

    init {
        getAllUsers()
    }

    var allUsersLiveData = MutableLiveData<UserResponse>()
    var error =    MutableLiveData<Throwable>()
    var loading : MutableLiveData<Boolean>? = MutableLiveData()

    fun getAllUsers()  = viewModelScope.launch {

        userRepository.getAllUsers()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatusEnum.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatusEnum.SUCCESS -> {
                        allUsersLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatusEnum.ERROR -> {
                        Log.e("ERROR", "${it.throwable}")
                        error.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}