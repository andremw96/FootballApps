package com.kade.dicoding.footballapps.feature.match

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import com.kade.dicoding.footballapps.R
import com.kade.dicoding.footballapps.R.id.button_search
import com.kade.dicoding.footballapps.adapter.pager.MatchPagerAdapter
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class MatchFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    private lateinit var mMatchPagerAdapter: MatchPagerAdapter

    private var menuItem: Menu? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_match, container, false)

        setHasOptionsMenu(true)

        viewPager = rootView.find(R.id.viewPagerContainer)
        tabs = rootView.find(R.id.tabs)

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
        return when (item.itemId) {
            button_search -> {
                ctx.startActivity<SearchMatchActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}