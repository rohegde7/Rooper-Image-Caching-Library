package com.rohegde7.rooper.imagedownload

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import android.widget.Toast
import com.rohegde7.rooper.UiUtil
import com.rohegde7.rooper.caching.CachingUtil
import com.rohegde7.rooper.network.CustomRetrofitProvider
import com.rohegde7.rooper.network.ImageDownloadApi
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

object Rooper {

    var mImageDownloadApi: ImageDownloadApi? = null

    fun downloadImage(context: Context, url: String, imageView: ImageView) {

        CachingUtil.getCachedImage(url)?.let {
            imageView.setImageBitmap(it)
            showImageLoadedFromCacheToast(context)
            return
        }

        UiUtil.displayProgress(context, "Downloading image...")

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
                    CachingUtil.cacheImage(url, image)
                    imageView.setImageBitmap(image)

                    UiUtil.hideProgress()
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    val error = e
                }
            })
    }

    private fun showImageLoadedFromCacheToast(context: Context) {
        Toast.makeText(context, "Image loaded from Cache!", Toast.LENGTH_SHORT).show()
    }
}