package com.kipalog.mobile.ui.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class HomePagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val tabTitles = arrayOf("HOT", "NEWEST", "TIL")

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HotFragment()
            1 -> NewestFragment()
            2 -> NewestFragment()
            else -> NewestFragment()
        }

    }

    override fun getCount(): Int = tabTitles.count()
}