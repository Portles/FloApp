package com.h5190001.flo.data.models

class UserResponse : ArrayList<UserResponseItem>()

data class UserResponseItem(
    val email: String?,
    val userName: String?,
    val password: String?,
    val pp_loc: String?
)
