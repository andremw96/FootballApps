package com.kade.dicoding.footballapps.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kade.dicoding.footballapps.feature.favorite.ListFavoriteFragment

class FavoritePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return ListFavoriteFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return 2
    }
}