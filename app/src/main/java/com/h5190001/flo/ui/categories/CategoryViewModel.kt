package com.h5190001.flo.ui.categories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190001.flo.data.repository.CategoryRepository
import com.h5190001.flo.data.models.CategoryResponse
import com.h5190001.flo.utils.Constants.VIEWMODEL_ERROR
import com.h5190001.flo.utils.enums.ResourceStatusEnum
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private  val categotyRepository: CategoryRepository = CategoryRepository()

    init {
        getAllCategorys()
    }

    var categorysLiveData = MutableLiveData<CategoryResponse>()
    var error =    MutableLiveData<Throwable>()
    var loading : MutableLiveData<Boolean>? = MutableLiveData()

    fun getAllCategorys()  = viewModelScope.launch {

        categotyRepository.getAllCategorys()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatusEnum.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatusEnum.SUCCESS -> {
                        categorysLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatusEnum.ERROR -> {
                        Log.e(VIEWMODEL_ERROR, "${it.throwable}")
                        error.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}