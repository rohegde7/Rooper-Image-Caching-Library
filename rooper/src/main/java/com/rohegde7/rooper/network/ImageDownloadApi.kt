package com.rohegde7.rooper.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ImageDownloadApi {

    @GET("img_api/AQ5JHo6Srk0.jpg")
    fun downloadImage(): Single<ResponseBody>
}