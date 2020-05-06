package com.rohegde7.rooper.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class LoggerInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        Log.e("API call", "${request.url()}")

        val response = chain.proceed(request)

        return response
    }
}