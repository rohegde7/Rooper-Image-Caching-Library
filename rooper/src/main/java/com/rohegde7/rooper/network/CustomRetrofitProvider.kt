package com.rohegde7.rooper.network

import com.google.gson.GsonBuilder
import com.rohegde7.rooper.network.interceptor.CachingInterceptor
import com.rohegde7.rooper.network.interceptor.LoggerInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object CustomRetrofitProvider {

    val gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getMyHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    private fun getMyHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .addNetworkInterceptor(CachingInterceptor())
//            .addNetworkInterceptor(ForceCacheInterceptor())
            .addNetworkInterceptor(LoggerInterceptor())
        return builder.build()
    }
}