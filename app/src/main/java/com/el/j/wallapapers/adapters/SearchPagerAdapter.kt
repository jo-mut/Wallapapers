package com.el.j.wallapapers.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.el.j.wallapapers.fragments.CollectionFragments
import com.el.j.wallapapers.fragments.PhotosFragment
import com.el.j.wallapapers.fragments.SearchCollectionsFragments
import com.el.j.wallapapers.fragments.SearchPhotosFragment

class SearchPagerAdapter: FragmentPagerAdapter {
    constructor(fm: FragmentManager?) : super(fm)

    override fun getItem(position: Int): Fragment {
        return when(position) {
            1 -> {
                SearchPhotosFragment();
            }else -> {
                SearchCollectionsFragments();
            }
        }
    }

    override fun getCount(): Int {
        return 2;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            1 -> {
                "Photos"
            }else -> {
                "Collections"
            }
        }
    }
}