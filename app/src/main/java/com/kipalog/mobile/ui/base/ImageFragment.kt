package com.kipalog.mobile.ui.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kipalog.mobile.R
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.request.RequestOptions

class ImageFragment : BaseFragment() {
    override fun layoutId(): Int = R.layout.fragment_image

    override fun initView(view: View) {
        super.initView(view)
        arguments?.getString(BUNDLE_IMAGE)?.let { url ->
            context?.let {
                val imageView: ImageView = view.findViewById(R.id.iv)
                imageView.setOnClickListener { callback?.invoke() }
                val options = RequestOptions()
                options.diskCacheStrategy(DiskCacheStrategy.ALL)
                val centerCrop = arguments?.getBoolean(BUNDLE_IMAGE_CENTER_CROP)
                if (centerCrop != null && centerCrop) {
                    options.centerCrop()
                } else {
                    options.fitCenter()
                }
                Glide.with(it)
                        .load(url)
                        .apply(options)
                        .into(imageView)
            }
        }
    }

    var callback: (() -> Unit)? = null

    companion object {
        private const val BUNDLE_IMAGE = "BUNDLE_IMAGE"
        private const val BUNDLE_IMAGE_CENTER_CROP = "BUNDLE_IMAGE_CENTER_CROP"
        fun newInstance(url: String, imageAspectCenterCrop: Boolean = false): ImageFragment {
            val imageFragment = ImageFragment()
            val args = Bundle()
            args.putString(BUNDLE_IMAGE, url)
            if (imageAspectCenterCrop) {
                args.putBoolean(BUNDLE_IMAGE_CENTER_CROP, true)
            }
            imageFragment.arguments = args
            return imageFragment
        }
    }
}