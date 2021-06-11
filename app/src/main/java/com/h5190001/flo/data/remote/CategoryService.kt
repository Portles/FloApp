package com.h5190001.flo.data.remote

import com.h5190001.flo.utils.Constants
import com.h5190001.flo.data.models.CategoryResponse
import com.h5190001.flo.utils.Constants.CATEGORY_SERVICES_PATH
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CategoryService {

    @GET(CATEGORY_SERVICES_PATH)
    suspend fun getAllCategorys(): Response<CategoryResponse>

    companion object  {

        fun build(): CategoryService {

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

            return retrofit.create(CategoryService::class.java)
        }
    }
}