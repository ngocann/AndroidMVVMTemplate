package com.kipalog.mobile.ui.cafeDetail

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kipalog.mobile.R
import com.kipalog.mobile.adapter.PostAdapter
import com.kipalog.mobile.databinding.FragmentHotBinding
import com.kipalog.mobile.databinding.FragmentNewestBinding
import com.kipalog.mobile.extension.*
import com.kipalog.mobile.model.Cafe
import com.kipalog.mobile.ui.base.BaseDaggerFragment
import com.kipalog.mobile.ui.base.ImageFragment
import com.kipalog.mobile.ui.base.ListFragmentPagerAdapter
import com.kipalog.mobile.viewmodel.CafeDetailViewModel
import com.kipalog.mobile.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_cafe_detail.*
import org.parceler.Parcels
import java.util.jar.Manifest

class CafeDetailFragment : BaseDaggerFragment<CafeDetailViewModel>(), PostAdapter.OnItemClickListener {
    override fun classViewModel(): Class<CafeDetailViewModel> = CafeDetailViewModel::class.java
    override fun layoutId(): Int = R.layout.fragment_cafe_detail
    var onImageClicked : OnImageClicked? = null
    private var mMap: GoogleMap? = null

    override fun progressBarId(): Int = R.id.progressBar
    override fun initView(view: View) {
        super.initView(view)
        viewmodel?.modelCafe?.observe(this, Observer { initViewPager(it) })
        viewmodel?.modelFacebook?.observe(this, Observer { showFacebook(it) })
        viewmodel?.modelPhone?.observe(this, Observer { showPhone() })
        viewmodel?.modelInstagram?.observe(this, Observer { showInstagram(it) })
        viewmodel?.modelLatLng?.observe(this, Observer { updateMap() })
        ivShare.setOnClickListener { openShare() }
        ivBack.setOnClickListener { activity?.onBackPressed() }
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync {
            mMap = it
            updateMap()
        }

        arguments?.let {
            val cafe : Cafe = Parcels.unwrap(it.getParcelable(BUNDLE_CAFE))
            viewmodel?.initData(cafe)
        }
    }

    fun updateMap() {
        viewmodel?.modelLatLng?.value?.let {
            mMap?.addMarker( MarkerOptions().position(it))
            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(it, 12.0F))
            mMap?.setOnMapClickListener { openGoogleMAp() }
        }
    }

    fun openGoogleMAp() {
         viewmodel?.cafe?.address?.let {
             val gmmIntentUri = Uri.parse("geo:0,0?q=$it")
             val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
             mapIntent.setPackage("com.google.android.apps.maps")
             startActivity(mapIntent)
        }
    }

    fun openShare() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }
        startActivity(sendIntent)
    }

    fun initViewPager(it: Cafe?) {
        val listImageFragment = ArrayList<ImageFragment>()
        it?.images?.forEach {
            val imageFragment = ImageFragment.newInstance(it)
            imageFragment.callback = object : (() -> Unit) {
                override fun invoke() {
                    viewmodel?.modelCafe?.value?.images?.let { it1 -> onImageClicked?.imageClicked(it1) }
                }
            }
            listImageFragment.add(imageFragment)
        }
        val imageFragmentPager = ListFragmentPagerAdapter(childFragmentManager, listImageFragment)
        vpImages.interval = 5000
        vpImages.adapter = imageFragmentPager
        vpImages.startAutoScroll()
    }

    interface OnImageClicked {
        fun imageClicked(images : List<String>)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQ_PERMISSION_CALL_PHONE) {
            if (permissions[0] == android.Manifest.permission.CALL_PHONE &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showPhone()
            }
        }
    }

    private fun showInstagram(instagram: String?) {
        instagram?.let { context?.openInstagram(instagram) }
    }

    private fun showPhone() {
        activity?.hasPermission(android.Manifest.permission.CALL_PHONE)?.let {
            if (it) viewmodel?.modelPhone?.value?.let { context?.openCaller(it) }
            else activity?.requestPermissionsSafely(arrayOf(android.Manifest.permission.CALL_PHONE), REQ_PERMISSION_CALL_PHONE)
        }
    }
    private fun showFacebook(page: String?) {
        page?.let { context?.openFacebook(it) }
    }
    override fun onItemClick(position: Int) {

    }

    companion object {
        const val BUNDLE_CAFE = "BUNDLE_CAFE"
        const val REQ_PERMISSION_CALL_PHONE: Int = 0x501
        fun newInstance(cafe: Cafe) : CafeDetailFragment {
            val cafeDetailFragment = CafeDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_CAFE, Parcels.wrap(cafe))
            cafeDetailFragment.arguments = bundle
            return cafeDetailFragment
        }
    }


}