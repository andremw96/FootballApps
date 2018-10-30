package com.example.wijaya_pc.footballapps.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.wijaya_pc.footballapps.feature.match.ListMatchFragment

class MatchPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return ListMatchFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return 2
    }
}