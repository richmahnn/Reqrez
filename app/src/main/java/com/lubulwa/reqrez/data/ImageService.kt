package com.lubulwa.reqrez.data

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.lubulwa.reqrez.R
import javax.inject.Inject

class ImageService @Inject constructor(private val context: Context) {

  private val imageHeight = context.resources.getDimensionPixelSize(R.dimen.user_list_item_image_size)
  private val imageWidth = imageHeight

  fun load(url: String): RequestBuilder<Drawable> {
    return Glide.with(context)
      .asDrawable()
      .apply(RequestOptions.circleCropTransform()
        .error(R.mipmap.ic_launcher)
        .fallback(R.mipmap.ic_launcher)
        .override(imageWidth, imageHeight)
        .diskCacheStrategy(RESOURCE))
      .load(url)
  }
}
