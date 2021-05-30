package com.h5190001.flo.services

import com.h5190001.flo.data.datasource.resources.Constants
import com.h5190001.flo.models.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserService {
    //https://jsonplaceholder.typicode.com/users
    @GET("/Portles/FloApp/master/datas/users.json")
    suspend fun getAllUsers(): Response<UserResponse>

    //https://jsonplaceholder.typicode.com/posts
    //@GET("posts")
    //suspend fun getAllPosts(): Response<PostResponse>

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