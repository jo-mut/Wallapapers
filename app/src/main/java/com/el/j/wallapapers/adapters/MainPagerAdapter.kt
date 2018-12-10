package com.el.j.wallapapers.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.el.j.wallapapers.fragments.CollectionFragments
import com.el.j.wallapapers.fragments.PhotosFragment

class MainPagerAdapter: FragmentPagerAdapter{
    constructor(fm: FragmentManager?) : super(fm)

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                PhotosFragment();
            }else -> {
                CollectionFragments();
            }
        }
    }

    override fun getCount(): Int {
        return 2;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> {
                "Photos"
            }else -> {
                "Collections"
            }
        }
    }
}