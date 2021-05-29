package com.h5190001.flo.models

import com.google.gson.annotations.SerializedName

class CategoryResponse : ArrayList<CategoryResponseItem>()

data class CategoryResponseItem(
    @SerializedName("email")
    val email: String?,
    @SerializedName("username")
    val userName: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("pp_loc")
    val pp_loc: String?
)
