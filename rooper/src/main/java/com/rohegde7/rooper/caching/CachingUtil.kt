package com.rohegde7.rooper.caching

import android.graphics.Bitmap

object CachingUtil {

    /*
    * Used to store the image URL along with the downloaded image
    * */
    private lateinit var mCachedImages: HashMap<String, Bitmap>

    fun getCachedImage(url: String): Bitmap? {

        for (item in mCachedImages) if (item.key == url) return item.value

        return null
    }

    fun cacheImage(url: String, bitmap: Bitmap) {
        mCachedImages.put(url, bitmap)
    }

    fun initCaching() {
        mCachedImages = HashMap()
    }

    fun clearCache() {
        mCachedImages.clear()
    }
}