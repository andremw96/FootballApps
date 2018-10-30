package com.example.wijaya_pc.footballapps.ui

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.R.color.colorAccent
import com.example.wijaya_pc.footballapps.R.color.colorPrimary
import com.example.wijaya_pc.footballapps.feature.team.DetailTeamActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DetailTeamUI : AnkoComponent<DetailTeamActivity> {
    override fun createView(ui: AnkoContext<DetailTeamActivity>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            swipeRefreshLayout {
                id = R.id.swipeRefreshDetailTeam

                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                scrollView {
                    isVerticalScrollBarEnabled = false
                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            padding = dip(10)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL

                            //team badge
                            imageView{
                                id = R.id.team_badgeDetailTeam
                            }.lparams(height = dip(75))

                            //team name
                            textView {
                                id = R.id.team_nameDetailTeam
                                this.gravity = Gravity.CENTER
                                textSize = 20f
                                textColor = ContextCompat.getColor(context, colorAccent)
                            }.lparams{
                                topMargin = dip(5)
                            }

                            //team formed year
                            textView{
                                id = R.id.team_formedyearDetailTeam
                                this.gravity = Gravity.CENTER
                            }

                            //team stadium
                            textView {
                                id = R.id.team_stadiumDetailTeam
                                this.gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(context, colorPrimary)
                            }

                            //team descrption
                            textView{
                                id = R.id.team_descDetailTeam
                            }.lparams{topMargin = dip(20)}
                        }

                        progressBar {
                            id = R.id.progressBarDetailTeam
                        }.lparams{ centerHorizontally() }
                    }
                }
            }
        }
    }
}