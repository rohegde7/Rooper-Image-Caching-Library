package com.rohegde7.rooper.ignore.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request.Builder
import okhttp3.Response

class ForceCacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val builder: Builder = chain.request().newBuilder()

//        if (!IsInternetAvailable())   // TODO
            builder.cacheControl(CacheControl.FORCE_CACHE);

        return chain.proceed(builder.build());
    }
}