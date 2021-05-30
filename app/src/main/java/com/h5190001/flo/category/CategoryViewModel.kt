package com.h5190001.flo.category

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190001.flo.data.repository.CategoryRepository
import com.h5190001.flo.models.CategoryResponse
import com.h5190001.flo.utils.ResourceStatus
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private  val categotyRepository: CategoryRepository = CategoryRepository()

    init {
        getAllCategorys()
    }

    var categorysLiveData : MutableLiveData<CategoryResponse>? = null
    var error :     MutableLiveData<Throwable>? = null
    var loading :    MutableLiveData<Boolean>? = null

    fun getAllCategorys()  = viewModelScope.launch {

        categotyRepository.getAllCategorys()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        categorysLiveData?.postValue(it.data!!)
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