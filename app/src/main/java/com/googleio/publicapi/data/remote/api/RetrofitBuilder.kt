package com.googleio.publicapi.data.remote.api

import com.googleio.publicapi.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    private val logger : HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okhttp : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .addInterceptor(Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder().build()
            chain.proceed(request)
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.WEBSITE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttp)
        .build()

    fun <T> build(serviceType : Class<T>) : T {
        return retrofit.create(serviceType)
    }

}