package com.example.wijaya_pc.footballapps.feature.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.R.id.*
import com.example.wijaya_pc.footballapps.feature.favorite.FavoriteFragment
import com.example.wijaya_pc.footballapps.feature.match.MatchFragment
import com.example.wijaya_pc.footballapps.feature.team.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                tab_matches -> {
                    loadMatchFragment()
                }

                tab_teams -> {
                    loadTeamsFragment()
                }

                favorites -> {
                    loadFavoritesFragment()
                }
            }
            true
        }
        bottom_navigation.selectedItemId = tab_matches

    }

    private fun loadMatchFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.main_container,
                MatchFragment(), MatchFragment::class.java.simpleName
            )
            .commit()
    }

    private fun loadTeamsFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.main_container,
                TeamsFragment(), TeamsFragment::class.java.simpleName
            )
            .commit()
    }

    private fun loadFavoritesFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.main_container,
                FavoriteFragment(), FavoriteFragment::class.java.simpleName
            )
            .commit()
    }

}
