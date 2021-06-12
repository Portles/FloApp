package com.h5190001.flo.utils

import com.h5190001.flo.utils.enums.ResourceStatusEnum

sealed class Resource<T>(
    val data: T?,
    val throwable: Throwable?,
    val status: ResourceStatusEnum
) {
    class Loading<T> : Resource<T>(null, null, ResourceStatusEnum.LOADING)
    class Success<T>(data: T?) : Resource<T>(data, null, ResourceStatusEnum.SUCCESS)
    class Error<T>(exception: Exception) : Resource<T>(null, exception, ResourceStatusEnum.ERROR)
}