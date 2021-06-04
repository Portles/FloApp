package com.h5190001.flo.models

import com.google.gson.annotations.SerializedName

class CategoryResponse : ArrayList<CategoryResponseItem>()

data class CategoryResponseItem(
    @SerializedName("Items")
    val Items: List<Item>?=null,
    @SerializedName("categoryName")
    val categoryName: String?=null,
    @SerializedName("category_pic_loc")
    val category_pic_loc: String?=null
)

data class Item(
    @SerializedName("details")
    val details: Details?=null,
    @SerializedName("name")
    val name: String?=null,
    @SerializedName("pic_loc")
    val pic_loc: String?=null,
    @SerializedName("specs")
    val specs: Specs?=null
)

data class Details(
    @SerializedName("insideMaterial")
    val insideMaterial: String?=null,
    @SerializedName("outsideMaterial")
    val outsideMaterial: String?=null,
    @SerializedName("sole")
    val sole: String?=null
)

data class Specs(
    @SerializedName("brand")
    val brand: String?=null,
    @SerializedName("color")
    val color: String?=null,
    @SerializedName("gender")
    val gender: String?=null,
    @SerializedName("modal")
    val modal: String?=null,
    @SerializedName("saya")
    val saya: String?=null,
    @SerializedName("sku")
    val sku: String?=null
)