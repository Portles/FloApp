package com.h5190001.flo.utils

import com.google.gson.Gson
import com.h5190001.flo.models.CategoryResponse
import com.h5190001.flo.models.CategoryResponseItem
import com.h5190001.flo.models.Item

object ObjectUtil {
    fun <T> objectToString(objectData: T): String {
        val gson = Gson()
        return gson.toJson(objectData)
    }

    fun ObjeToJsonString(liste: List<Item>?): String? {
        val gson = Gson()
        return gson.toJson(liste)
    }

    fun jsonStringToObje(jsonString: String?): List<Item> {
        val gson = Gson()
        return gson.fromJson(jsonString , Array<Item>::class.java).toList()
    }

    fun jsonStringToItemObje(jsonString: String?): Item {
        val gson = Gson()
        return gson.fromJson(jsonString , Item::class.java)
    }
}