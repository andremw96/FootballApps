package com.example.wijaya_pc.footballapps.feature.favoritematch

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.adapter.pager.FavoritePagerAdapter
import org.jetbrains.anko.find


class FavoriteFragment : Fragment() {
    private lateinit var viewPager : ViewPager
    private lateinit var tabs : TabLayout

    private lateinit var mFavoritePagerAdapter: FavoritePagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.fragment_favorite, container, false)

        viewPager = rootView.find(R.id.viewPagerFavorite) as ViewPager
        tabs = rootView.find(R.id.tabsFavorite) as TabLayout

        mFavoritePagerAdapter = FavoritePagerAdapter(childFragmentManager)
        viewPager.adapter = mFavoritePagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

        return rootView
    }
}
