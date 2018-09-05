package com.kipalog.mobile.ui.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kipalog.mobile.R
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.request.RequestOptions



class ImageFragment : BaseFragment() {
    override fun layoutId(): Int = R.layout.fragment_image

    override fun initView(view : View) {
        super.initView(view)
        arguments?.getString(BUNDLE_IMAGE)?.let { url ->
            context?.let {
                val imageView : ImageView = view.findViewById(R.id.iv)
                imageView.setOnClickListener { callback?.invoke() }
                val options = RequestOptions()
                options.centerCrop()
                Glide.with(it)
                    .load(url)
                    .apply(options)
                    .into(imageView)
            }
        }
    }

    var callback : (() -> Unit)? = null

    companion object {
        private const val BUNDLE_IMAGE = "BUNDLE_IMAGE"
        fun newInstance(url : String) : ImageFragment {
            val imageFragment = ImageFragment()
            val args = Bundle()
            args.putString(BUNDLE_IMAGE, url )
            imageFragment.arguments = args
            return imageFragment
        }
    }
}