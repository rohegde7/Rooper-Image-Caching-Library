package com.rohegde7.rooper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rohegde7.rooper.imagedownload.Rooper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val image = Rooper.downloadImage("http://www.3ue.xyz/")
//            Rooper.downloadImage("https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Google_Images_2015_logo.svg/2880px-Google_Images_2015_logo.svg.png/")
//            Rooper.downloadImage("https://imagevars.gulfnews.com/2019/12/05/Hrithik-Roshan_16ed68d6e61_large.jpg/")

            val i = image

            imageview.setImageBitmap(image)

        }
    }
}
