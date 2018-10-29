package com.example.wijaya_pc.footballapps.ui

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.wijaya_pc.footballapps.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class MatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent)
            orientation = LinearLayout.VERTICAL

            cardView {

                textView {
                    id = R.id.match_date
                    textSize = 12f
                    textColor = Color.GREEN
                    gravity = Gravity.CENTER_HORIZONTAL
                }

                linearLayout {
                    lparams(width = matchParent)

                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    // home team
                    textView {
                        id = R.id.match_home_team
                        textSize = 16f
                        gravity = Gravity.START
                    }.lparams(width = dip(0), weight = 1F)

                    // home team score
                    textView {
                        id = R.id.match_home_score
                        textSize = 16f
                        gravity = Gravity.END
                        text = " "
                    }.lparams(width = dip(0), weight = 1F) {
                        gravity = Gravity.CENTER_VERTICAL
                    }

                    // string VS
                    textView {
                        text = resources.getString(R.string.vs_string)
                        gravity = Gravity.CENTER
                        textSize = 16f
                    }.lparams(width = dip(0), weight = 1F) {
                        gravity = Gravity.CENTER_VERTICAL
                    }

                    // away team score
                    textView {
                        id = R.id.match_away_score
                        textSize = 16f
                        gravity = Gravity.START
                        text = " "
                    }.lparams(width = dip(0), weight = 1F) {
                        gravity = Gravity.CENTER_VERTICAL
                    }

                    textView {
                        id = R.id.match_away_team
                        textSize = 16f
                        gravity = Gravity.END
                    }.lparams(width = dip(0), weight = 1F)
                }
            }.lparams(width = matchParent) {
                topMargin = dip(8)
                bottomMargin = dip(4)
                leftMargin = dip(10)
                rightMargin = dip(10)
            }
        }
    }
}