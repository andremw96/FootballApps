package com.example.wijaya_pc.footballapps.feature.match

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.adapter.MatchPagerAdapter
import org.jetbrains.anko.find

class MatchFragment : Fragment() {

    private lateinit var viewPager : ViewPager
    private lateinit var tabs : TabLayout

    private lateinit var mMatchPagerAdapter: MatchPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.fragment_match, container, false)

        viewPager = rootView.find(R.id.viewPagerContainer) as ViewPager
        tabs = rootView.find(R.id.tabs) as TabLayout

        mMatchPagerAdapter = MatchPagerAdapter(childFragmentManager)
        viewPager.adapter = mMatchPagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

        return rootView
    }
}