package com.h5190001.flo.data.remote

import com.h5190001.flo.utils.Constants
import com.h5190001.flo.data.models.UserResponse
import com.h5190001.flo.utils.Constants.USER_SERVICES_PATH
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserService {

    @GET(USER_SERVICES_PATH)
    suspend fun getAllUsers(): Response<UserResponse>

    companion object  {

        fun build(): UserService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHtppClient =  OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHtppClient)
                .build()

            return retrofit.create(UserService::class.java)
        }
    }
}