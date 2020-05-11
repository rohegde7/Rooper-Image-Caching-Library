package com.rohegde7.rooper.caching

import android.graphics.Bitmap

/*
 * Static variable used to store the image URL along with the downloaded image
 * */
val mCachedImages = HashMap<String, Bitmap>()

object CachingUtil {

    fun getCachedImage(url: String): Bitmap? {

        for (item in mCachedImages)
            if (item.key == url)
                return item.value

        return null
    }

    fun cacheImage(url: String, bitmap: Bitmap) {
        mCachedImages.put(url, bitmap)
    }

    fun clearCache() {
        mCachedImages.clear()
    }
}