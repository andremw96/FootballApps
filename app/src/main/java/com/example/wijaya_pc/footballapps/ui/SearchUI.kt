package com.example.wijaya_pc.footballapps.ui

import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.feature.match.SearchMatchActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class SearchUI : AnkoComponent<SearchMatchActivity> {
    override fun createView(ui: AnkoContext<SearchMatchActivity>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL

            themedToolbar(R.style.ThemeOverlay_AppCompat_Dark) {
                id = R.id.toolbar_search
                lparams(width = matchParent)
                backgroundColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
            }

            swipeRefreshLayout {
                id = R.id.swipe_refresh_search
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    recyclerView {
                        id = R.id.rv_search
                        lparams(width = matchParent, height = wrapContent)
                    }

                    progressBar {
                        id = R.id.progress_bar_search
                    }.lparams{
                        centerHorizontally()
                    }
                }

            }.lparams(width = matchParent, height = matchParent)
        }
    }
}