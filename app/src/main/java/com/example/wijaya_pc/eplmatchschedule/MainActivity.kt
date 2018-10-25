package com.example.wijaya_pc.eplmatchschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.wijaya_pc.eplmatchschedule.R.id.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                tab_last_match -> {
                    val matchFragment = MatchFragment.newInstance(0)
                    loadMatchFragment(savedInstanceState, matchFragment)
                }

                tab_next_match -> {
                    val matchFragment = MatchFragment.newInstance(1)
                    loadMatchFragment(savedInstanceState, matchFragment)
                }

                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = tab_last_match

    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteMatchesFragment(), FavoriteMatchesFragment::class.java.simpleName)
                .commit()
    }

    private fun loadMatchFragment(
        savedInstanceState: Bundle?,
        matchFragment: MatchFragment
    ) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, matchFragment, MatchFragment::class.java.simpleName)
                .commit()
    }

}
