package com.rohegde7.rooper.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageDownloadApi {

    @GET("{path}")
    fun downloadImage(@Path("path", encoded = true) path: String): Single<ResponseBody>
}