package com.rohegde7.rooper.imagedownload

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.rohegde7.rooper.network.CustomRetrofitProvider
import com.rohegde7.rooper.network.ImageDownloadApi
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

object Rooper {

    var mImageDownloadApi: ImageDownloadApi? = null

    fun downloadImage(url: String): Bitmap? {

        var bitmap: Bitmap? = null

        mImageDownloadApi = CustomRetrofitProvider
            .getRetrofit(url)
            .create(ImageDownloadApi::class.java)

        mImageDownloadApi!!
            .downloadImage()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseBody> {
                override fun onSuccess(bitmapImage: ResponseBody) {
                    val image: Bitmap = BitmapFactory.decodeStream(bitmapImage.byteStream())
                    bitmap = image
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    val error = e
                }
            })

        return bitmap
    }
}