package com.h5190001.flo.utils

import com.google.gson.Gson
import com.h5190001.flo.models.CategoryResponse

object ObjectUtil {
    fun CategoryToJsonString(cat: CategoryResponse): String? {
        val gson = Gson()
        return gson.toJson(cat)
    }

    fun jsonStringToCategory(jsonString: String?): CategoryResponse? {
        val gson = Gson()
        return gson.fromJson(jsonString, CategoryResponse::class.java)
    }
}