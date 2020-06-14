package com.example.upstartswork.data

import com.example.upstartswork.model.CardViewItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {
    private const val API_BASE_URL = "https://static.upstarts.work"
    private var serviceApi: ServiceApi? = null

    fun build() : ServiceApi? {
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        serviceApi = retrofit.create(
            ServiceApi::class.java)

        return serviceApi as ServiceApi
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServiceApi {
        @GET("/tests/jeans/jeans-default.json")
        fun getGoods(): Call<List<CardViewItem>>
    }
}