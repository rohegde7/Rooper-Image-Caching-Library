package com.rohegde7.rooper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.rohegde7.rooper.databinding.ActivityImageLoadingBinding
import com.rohegde7.rooper.imagedownload.Rooper
import kotlinx.android.synthetic.main.activity_image_loading.*
import kotlinx.android.synthetic.main.activity_main.*

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
        wireEventHandlers()
    }

    private fun setUpDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_loading)
        mBinding.viewmodel = mViewModel
    }

    private fun wireEventHandlers() {
        download_image_button.setOnClickListener {
            downloadImage()
        }
    }

    private fun downloadImage() {
        val image = Rooper.downloadImage("http://www.3ue.xyz/")
        imageview.setImageBitmap(image)
    }
}
