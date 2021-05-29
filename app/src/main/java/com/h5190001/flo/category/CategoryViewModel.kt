package com.h5190001.flo.category

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190001.flo.data.locale.CategoryDataSource
import com.h5190001.flo.data.repository.CategoryRepository
import com.h5190001.flo.models.CategoryResponse
import com.h5190001.flo.utils.ResourceStatus
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private  val categotyRepository: CategoryRepository = CategoryRepository()

    init {
        getAllCategorys()
    }

    var allCategorysLiveData = MutableLiveData<CategoryResponse>()
    var error =     MutableLiveData<Throwable>()
    var loading =    MutableLiveData<Boolean>()

    fun getAllCategorys()  = viewModelScope.launch {

        categotyRepository.getAllCategorys()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        allCategorysLiveData?.postValue(it.data!!)
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