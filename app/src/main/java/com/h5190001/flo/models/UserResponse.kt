package com.h5190001.flo.models
import com.google.gson.annotations.SerializedName

class UserResponse : ArrayList<UserResponseItem>()

data class UserResponseItem(
        @SerializedName("userName")
    val userName: String?,
        @SerializedName("password")
    val password: String?,
        @SerializedName("pp_loc")
    val pp_loc: String?
)
