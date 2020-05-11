package com.rohegde7.rooper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rohegde7.rooper.caching.CachingUtil
import com.rohegde7.rooper.enum.Action

class ImageLoadingViewModel : ViewModel(){

    val mRepository = ImageLoadingRepository()

    val imageAction = MutableLiveData<Action>()

    fun downloadImage() {
        imageAction.value = Action.DOWNLOAD_IMAGE
    }

    fun clearImage() {
        imageAction.value = Action.CLEAR_IMAGE
    }

    fun clearCache() {
        imageAction.value = Action.CLEAR_CACHE
    }
}