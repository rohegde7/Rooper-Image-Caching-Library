package com.rohegde7.rooper

import android.app.Application
import com.rohegde7.rooper.caching.CachingUtil

class RooperApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        mAppContext = this
        CachingUtil.initCaching()
    }

    override fun onLowMemory() {
        CachingUtil.clearCache()

        super.onLowMemory()
    }

    companion object {
        var mAppContext: RooperApplication? = null
    }
}