package com.kipalog.mobile.ui.cafeDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kipalog.mobile.R
import com.kipalog.mobile.model.Cafe
import com.kipalog.mobile.ui.base.BaseDaggerActivity
import com.kipalog.mobile.ui.cafeDetail.CafeDetailFragment.OnImageClicked
import com.kipalog.mobile.viewmodel.CafeDetailViewModel
import org.parceler.Parcels

class CafeDetailDaggerActivity : BaseDaggerActivity<CafeDetailViewModel>() {
    override fun classViewModel() = CafeDetailViewModel::class.java

    companion object {
        const val TAG_CAFE_DETAIL = "TAG_CAFE_DETAIL"
        const val BUNDLE_CAFE = "BUNDLE_CAFE"
        fun start(context: Context, cafe : Cafe? = null) {
            val intent = Intent(context, CafeDetailDaggerActivity::class.java)
            cafe?.let { intent.putExtra(BUNDLE_CAFE, Parcels.wrap(it)) }
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_detail)
        if (supportFragmentManager.findFragmentByTag(TAG_CAFE_DETAIL) == null ) {
            var cafeDetailFragment : CafeDetailFragment = if (intent.hasExtra(BUNDLE_CAFE)) {
                CafeDetailFragment.newInstance(Parcels.unwrap(intent.getParcelableExtra(BUNDLE_CAFE)))
            }else {
                CafeDetailFragment()
            }
            cafeDetailFragment.onImageClicked = object : OnImageClicked {
                override fun imageClicked(images: List<String>) {
                    addImageDetail(images)
                }
            }
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.content, cafeDetailFragment)
                    .commit()
        }
    }
    fun addImageDetail(images : List<String>) {
        val imageDetailFragment = CafeDetailImageFragment.newInstanse(images)
        supportFragmentManager.beginTransaction()
                .add(R.id.content, imageDetailFragment)
                .addToBackStack(null)
                .commit()
    }
}