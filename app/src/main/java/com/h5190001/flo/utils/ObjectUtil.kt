package com.h5190001.flo.utils

import com.google.gson.Gson
import com.h5190001.flo.data.models.Item
import com.h5190001.flo.data.models.UserResponseItem

object ObjectUtil {
    fun <T> objectToString(objectData: T): String {
        val gson = Gson()
        return gson.toJson(objectData)
    }

    fun jsonStringToUserObject(jsonString: String?): UserResponseItem {
        val gson = Gson()
        return gson.fromJson(jsonString , UserResponseItem::class.java)
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