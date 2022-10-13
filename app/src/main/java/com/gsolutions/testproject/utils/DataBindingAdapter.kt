package com.gsolutions.testproject.utils

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gsolutions.testproject.R


object DataBindingAdapter {

    @BindingAdapter("android:image")
    @JvmStatic
    fun setImageViewResource(imageView: ImageView, uri: Uri?) {
        if (uri != null) {
            Glide.with(imageView.context)
                .load(uri) // Uri of the picture
                .into(imageView);
        }else
            imageView.setBackgroundResource(R.color.colorGreen)

    }

    @SuppressLint("ResourceAsColor")
    @BindingAdapter("android:image")
    @JvmStatic
    fun setImageViewResource(imageView: ImageView, type: Int) {
        Log.e("type", type.toString())

        if (type == Constants.TYPE_PHOTO_2)
            imageView.setBackgroundResource(R.color.colorGreen)
        else
           imageView.setBackgroundResource(R.color.colorLightBlue)

    }

}