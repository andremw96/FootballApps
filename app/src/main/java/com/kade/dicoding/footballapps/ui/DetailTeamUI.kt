package com.kade.dicoding.footballapps.ui

import android.graphics.Color
import android.support.design.widget.TabLayout
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.kade.dicoding.footballapps.R
import com.kade.dicoding.footballapps.feature.team.DetailTeamActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager

class DetailTeamUI : AnkoComponent<DetailTeamActivity> {
    override fun createView(ui: AnkoContext<DetailTeamActivity>): View = with(ui) {
        linearLayout {
            id = R.id.detail_team_UI
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            themedAppBarLayout(R.style.AppTheme_AppBarOverlay) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(10)
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER_HORIZONTAL

                    //team badge
                    imageView {
                        id = R.id.team_badgeDetailTeam
                    }.lparams(height = dip(75))

                    //team name
                    textView {
                        id = R.id.team_nameDetailTeam
                        this.gravity = Gravity.CENTER
                        textSize = 20f
                        textColor = Color.WHITE
                    }.lparams {
                        topMargin = dip(5)
                    }

                    //team formed year
                    textView {
                        id = R.id.team_formedyearDetailTeam
                        textColor = Color.WHITE
                        this.gravity = Gravity.CENTER
                    }

                    //team stadium
                    textView {
                        id = R.id.team_stadiumDetailTeam
                        this.gravity = Gravity.CENTER
                        textColor = Color.WHITE
                    }

                    // tab layout
                    themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                        lparams(matchParent) {
                            tabMode = TabLayout.MODE_FIXED
                        }
                        id = R.id.tabs_detail_team
                    }
                }
            }

            viewPager {
                id = R.id.viewpager_detail_team

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    progressBar {
                        id = R.id.progressBarDetailTeam
                    }.lparams { centerHorizontally() }
                }
            }.lparams(matchParent, matchParent)

        }
    }
}