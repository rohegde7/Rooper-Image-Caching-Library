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
import java.lang.Exception
import java.net.URL

object Rooper {

    var mImageDownloadApi: ImageDownloadApi? = null

    fun downloadImage(context: Context, url: String, imageView: ImageView) {

        if (!isValidUrl(url, context)) return

        val baseUrl = getBaseUrl(url)
        val path = getPath(url)

        CachingUtil.getCachedImage(url)?.let {
            imageView.setImageBitmap(it)
            showImageLoadedFromCacheToast(context)
            return
        }

        UiUtil.displayProgress(context, "Downloading image...")

        mImageDownloadApi = getImageDownloadApi(baseUrl)

        mImageDownloadApi!!
            .downloadImage(path)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseBody> {
                override fun onSuccess(bitmapImage: ResponseBody) {

                    try {
                        val image: Bitmap = BitmapFactory.decodeStream(bitmapImage.byteStream())
                        CachingUtil.cacheImage(url, image)
                        imageView.setImageBitmap(image)

                    } catch (exception: Exception) {
                        Toast.makeText(context, "Image type not supported!", Toast.LENGTH_SHORT)
                            .show()
                    }

                    UiUtil.hideProgress()
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    val error = e
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    UiUtil.hideProgress()
                }
            })
    }

    private fun showImageLoadedFromCacheToast(context: Context) {
        Toast.makeText(context, "Image loaded from Cache!", Toast.LENGTH_SHORT).show()
    }

    private fun getImageDownloadApi(url: String): ImageDownloadApi {
        return CustomRetrofitProvider
            .getRetrofit(url)
            .create(ImageDownloadApi::class.java)
    }

    private fun isValidUrl(url: String, context: Context): Boolean {
        try {
            val url = URL(url)
            return true

        } catch (exception: Exception) {
            Toast.makeText(context, "Invalid URL!", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun getBaseUrl(url: String): String {
        val url = URL(url)
        val base = "${url.protocol}://${url.host}/"

        return base
    }

    private fun getPath(url: String): String {
        val url = URL(url)
        var path = url.getFile()  // /img_api/pX9eRjli1ok.jpg

        return path.subSequence(1, path.length).toString()
    }
}