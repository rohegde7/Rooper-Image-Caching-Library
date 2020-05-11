package com.rohegde7.rooper.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rohegde7.rooper.enum.Action
import com.rohegde7.rooper.ui.ImageLoadingRepository

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