package com.rohegde7.rooper.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rohegde7.rooper.enum.Action
import com.rohegde7.rooper.ui.ImageLoadingRepository

class ImageLoadingViewModel : ViewModel() {

    val mRepository = ImageLoadingRepository()

    val imageAction = MutableLiveData<Action>()

    val imageUrl = ObservableField<String>("")

    fun downloadSampleImage() {
        imageAction.value = Action.DOWNLOAD_SAMPLE_IMAGE
    }

    fun clearImage() {
        imageAction.value = Action.CLEAR_IMAGE
    }

    fun clearCache() {
        imageAction.value = Action.CLEAR_CACHE
    }

    fun downloadImage() {
        imageAction.value = Action.DOWNLOAD_IMAGE
    }
}