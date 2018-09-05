package com.kipalog.mobile.ui.cafeDetail

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.kipalog.mobile.R
import com.kipalog.mobile.ui.base.BaseFragment
import com.kipalog.mobile.ui.base.ImageFragment
import com.kipalog.mobile.ui.base.ListFragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_cafe_detail_image.*
import java.util.ArrayList

class CafeDetailImageFragment : BaseFragment() {

    override fun layoutId() = R.layout.fragment_cafe_detail_image
    override fun initView(view: View) {
        super.initView(view)
        arguments?.getStringArrayList(BUNDLE_IMAGES)?.let {
            val listImageFragment = ArrayList<ImageFragment>()
            it?.forEach { listImageFragment.add(ImageFragment.newInstance(it)) }
            val imageFragmentPager = ListFragmentPagerAdapter(childFragmentManager, listImageFragment)
            viewPager.adapter = imageFragmentPager
        }
    }

    companion object {
        const val BUNDLE_IMAGES = "BUNDLE_IMAGES"
        fun newInstanse(images : List<String>) : CafeDetailImageFragment {
            val cafeDetailImageFragment = CafeDetailImageFragment()
            val args = Bundle()
            args.putStringArrayList(BUNDLE_IMAGES, images as ArrayList<String>?)
            cafeDetailImageFragment.arguments = args
            return cafeDetailImageFragment
        }
    }
}