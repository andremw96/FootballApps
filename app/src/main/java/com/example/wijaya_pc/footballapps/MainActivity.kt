package com.example.wijaya_pc.footballapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.wijaya_pc.footballapps.R.id.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                tab_last_match -> {
                    val matchFragment = MatchFragment.newInstance(0)
                    loadMatchFragment(matchFragment)
                }

                tab_next_match -> {
                    val matchFragment = MatchFragment.newInstance(1)
                    loadMatchFragment(matchFragment)
                }

                favorites -> {
                    loadFavoritesFragment()
                }
            }
            true
        }
        bottom_navigation.selectedItemId = tab_last_match

    }

    private fun loadFavoritesFragment() {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteMatchesFragment(), FavoriteMatchesFragment::class.java.simpleName)
                .commit()
    }

    private fun loadMatchFragment(matchFragment: MatchFragment) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, matchFragment, MatchFragment::class.java.simpleName)
                .commit()
    }

}
