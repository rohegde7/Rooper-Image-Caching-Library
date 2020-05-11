package com.rohegde7.rooper.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.rohegde7.rooper.R
import com.rohegde7.rooper.caching.CachingUtil
import com.rohegde7.rooper.databinding.ActivityImageLoadingBinding
import com.rohegde7.rooper.enum.Action
import com.rohegde7.rooper.imagedownload.Rooper
import kotlinx.android.synthetic.main.activity_image_loading.*

class ImageLoadingActivity : AppCompatActivity() {

    /*
     * Same instance of mViewModel will be maintained throughout the lifecycle of
     * this activity
     * */
    var mViewModel: ImageLoadingViewModel =
        NewInstanceFactory().create(ImageLoadingViewModel::class.java)

    lateinit var mBinding: ActivityImageLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpDataBinding()
        observeLiveData()
    }

    private fun setUpDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_loading)
        mBinding.viewmodel = mViewModel
    }

    private fun observeLiveData() {
        mViewModel.imageAction.observe(this, Observer {
            when (it) {
                Action.DOWNLOAD_SAMPLE_IMAGE -> downloadSampleImage()
                Action.DOWNLOAD_IMAGE -> downloadImage()
                Action.CLEAR_CACHE -> clearCache()
                Action.CLEAR_IMAGE -> image_view.setImageBitmap(null)
            }
        })
    }

    private fun clearCache() {
        CachingUtil.clearCache()
        Toast.makeText(this, "Cache cleared!", Toast.LENGTH_SHORT).show()
    }

    private fun downloadImage() {
        Rooper.downloadImage(this, mViewModel.imageUrl.get()!!, image_view)
    }

    private fun downloadSampleImage() {
        Rooper.downloadImage(this, "http://www.3ue.xyz/img_api/pX9eRjli1ok.jpg", image_view)
    }

    override fun onDestroy() {
        CachingUtil.clearCache()

        super.onDestroy()
    }
}