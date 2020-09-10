package com.gmail.tpt.grocerystore

import android.app.ActionBar
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/**
 *
 * Created by thet on 3/3/2020.
 *
 */
fun ImageView.load(@RawRes drawable: Int, onLoadFinished: () -> Unit = {}) {
    Glide.with(this)
        .load(drawable)
        .centerCrop()
        .dontAnimate()
        .dontTransform()
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadFinished()
                Logger.d("load failed => " + e?.message)
                e?.printStackTrace()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadFinished()
                Logger.d("loading => ${resource?.intrinsicWidth}x${resource?.intrinsicHeight}")
                return false
            }
        })
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(this)


}

