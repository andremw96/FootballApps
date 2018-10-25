package com.example.wijaya_pc.eplmatchschedule.ui

import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.wijaya_pc.eplmatchschedule.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class MatchUI : AnkoComponent<ViewGroup> {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            cardView {

                textView {
                    id = R.id.match_date
                    textSize = 12f
                    textColor = Color.GREEN
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)

                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    // home team
                    textView {
                        id = R.id.match_home_team
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                    }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    // home team score
                    textView {
                        id = R.id.match_home_score
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                        text = " "
                    }.lparams(width = dip(0), height = wrapContent, weight = 1F) {
                        gravity = Gravity.CENTER_VERTICAL
                    }

                    // string VS
                    textView {
                        text = resources.getString(R.string.vs_string)
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textSize = 16f
                    }.lparams(width = dip(0), height = wrapContent, weight = 1F) {
                        gravity = Gravity.CENTER_VERTICAL
                    }

                    // away team score
                    textView {
                        id = R.id.match_away_score
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                        text = " "
                    }.lparams(width = dip(0), height = wrapContent, weight = 1F) {
                        gravity = Gravity.CENTER_VERTICAL
                    }

                    textView {
                        id = R.id.match_away_team
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                    }.lparams(width = dip(0), height = wrapContent, weight = 1F)
                }
            }.lparams(width = matchParent, height = matchParent) {
                topMargin = dip(8)
                bottomMargin = dip(4)
                leftMargin = dip(10)
                rightMargin = dip(10)
            }
        }
    }
}