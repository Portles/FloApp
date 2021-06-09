package com.h5190001.flo.models


class CategoryResponse : ArrayList<CategoryResponseItem>()

data class CategoryResponseItem(
    val Items: List<Item>?,
    val categoryName: String?,
    val category_pic_loc: String?
)

data class Item(
    val details: Details?,
    val name: String?,
    val pic_loc: String?,
    val specs: Specs?
)

data class Details(
    val insideMaterial: String?,
    val outsideMaterial: String?,
    val sole: String?
)

data class Specs(
    val brand: String?,
    val color: String?,
    val gender: String?,
    val modal: String?,
    val saya: String?,
    val sku: String?
)