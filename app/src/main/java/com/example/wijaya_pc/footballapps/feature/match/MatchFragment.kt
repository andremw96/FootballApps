package com.example.wijaya_pc.footballapps.feature.match

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.R.id.button_search
import com.example.wijaya_pc.footballapps.adapter.pager.MatchPagerAdapter
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class MatchFragment : Fragment() {

    private lateinit var viewPager : ViewPager
    private lateinit var tabs : TabLayout

    private lateinit var mMatchPagerAdapter: MatchPagerAdapter

    private var menuItem: Menu? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.fragment_match, container, false)

        setHasOptionsMenu(true)

        viewPager = rootView.find(R.id.viewPagerContainer) as ViewPager
        tabs = rootView.find(R.id.tabs) as TabLayout

        mMatchPagerAdapter = MatchPagerAdapter(childFragmentManager)
        viewPager.adapter = mMatchPagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.search_toolbar_menu, menu)
        menuItem = menu

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            button_search -> {
                ctx.startActivity<SearchMatchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}