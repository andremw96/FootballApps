package com.example.wijaya_pc.footballapps.ui

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.feature.match.DetailMatchActivity
import org.jetbrains.anko.*

class DetailMatchUI : AnkoComponent<DetailMatchActivity> {

    override fun createView(ui: AnkoContext<DetailMatchActivity>): View = with(ui) {

        relativeLayout {
            lparams(width = matchParent)

            scrollView {
                id = R.id.detail_view
                lparams(width = matchParent)

                verticalLayout {
                    lparams(width = matchParent)
                    padding = dip(16)

                    //match date
                    textView {
                        id = R.id.detail_match_date
                        gravity = Gravity.CENTER
                    }.lparams(width = matchParent)

                    //match time
                    textView {
                        id = R.id.detail_match_time
                        gravity = Gravity.CENTER
                    }.lparams(width = matchParent)

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            // home
                            imageView {
                                id = R.id.detail_home_logo
                                setImageResource(R.drawable.img_default)
                            }.lparams(width = matchParent, height = dip(75))

                            textView(R.string.home_team) {
                                id = R.id.detail_match_home_team
                                gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                            }.lparams(width = matchParent)

                        }.lparams(width = dip(100), height = dip(100)) {
                            weight = 1F
                        }

                        // scoring
                        textView {
                            id = R.id.detail_match_home_score
                            gravity = Gravity.CENTER
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent) {
                            gravity = Gravity.CENTER_VERTICAL
                            weight = 1F
                        }

                        textView(R.string.vs_string) {
                            gravity = Gravity.CENTER
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent) {
                            gravity = Gravity.CENTER_VERTICAL
                            weight = 1F
                        }

                        textView {
                            id = R.id.detail_match_away_score
                            gravity = Gravity.CENTER
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent) {
                            gravity = Gravity.CENTER_VERTICAL
                            weight = 1F
                        }

                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            //away
                            imageView {
                                id = R.id.detail_away_logo
                                setImageResource(R.drawable.img_default)
                            }.lparams(width = matchParent, height = dip(75))

                            textView(R.string.away_team) {
                                id = R.id.detail_match_away_team
                                gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                            }.lparams(width = matchParent, height = matchParent)

                        }.lparams(width = dip(100), height = dip(100)) {
                            weight = 1F
                        }

                    }.lparams(width = matchParent)

                    // line separator
                    view {
                        backgroundResource = android.R.color.darker_gray
                    }.lparams(width = matchParent, height = dip(1)) {
                        bottomMargin = dip(10)
                    }


                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home team goals
                        textView {
                            id = R.id.detail_match_home_goals
                        }.lparams(width = dip(0), weight = 1F)

                        textView(R.string.goals) {
                            gravity = Gravity.CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), weight = 1F)

                        // away team goals
                        textView {
                            id = R.id.detail_match_away_goals
                        }.lparams(width = dip(0), weight = 1F)

                    }.lparams(width = matchParent)

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home shots
                        textView {
                            id = R.id.detail_match_home_shots
                        }.lparams(width = dip(0), weight = 1F)

                        textView(R.string.shots) {
                            gravity = Gravity.CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), weight = 1F)

                        // away shots
                        textView {
                            id = R.id.detail_match_away_shots
                            gravity = Gravity.RIGHT
                        }.lparams(width = dip(0), weight = 1F)

                    }.lparams(width = matchParent) {
                        topMargin = dip(10)
                    }

                    // line separator
                    view {
                        backgroundResource = android.R.color.darker_gray
                    }.lparams(width = matchParent, height = dip(1)) {
                        topMargin = dip(10)
                        bottomMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home formation
                        textView {
                            id = R.id.detail_match_home_formation
                        }.lparams(width = dip(0), weight = 1F)

                        //Lineups String
                        textView(R.string.lineups) {
                            gravity = Gravity.CENTER
                        }.lparams(width = dip(0), weight = 1F)

                        // away formation
                        textView {
                            id = R.id.detail_match_away_formation
                        }.lparams(width = dip(0), weight = 1F)

                    }.lparams(width = matchParent) {
                        topMargin = dip(10)
                    }


                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home goalkeeper
                        textView {
                            id = R.id.detail_match_home_goalkeeper
                        }.lparams(width = dip(0), weight = 1F)

                        textView(R.string.goal_keeper) {
                            gravity = Gravity.CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), weight = 1F)

                        // away goalkeeper
                        textView {
                            id = R.id.detail_match_away_goalkeeper
                        }.lparams(width = dip(0), weight = 1F)

                    }.lparams(width = matchParent) {
                        topMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home defense
                        textView {
                            id = R.id.detail_match_home_defense
                        }.lparams(width = dip(0), weight = 1f)

                        textView(R.string.defense) {
                            gravity = Gravity.CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), weight = 1F)

                        // away defense
                        textView {
                            id = R.id.detail_match_away_defense
                        }.lparams(width = dip(0), weight = 1F)

                    }.lparams(width = matchParent) {
                        topMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home midfield
                        textView {
                            id = R.id.detail_match_home_midfield
                        }.lparams(width = dip(0), weight = 1F)

                        textView(R.string.midfield) {
                            gravity = Gravity.CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), weight = 1F)

                        // away midfield
                        textView {
                            id = R.id.detail_match_away_midfield
                        }.lparams(width = dip(0), weight = 1F)

                    }.lparams(width = matchParent) {
                        topMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home forward
                        textView {
                            id = R.id.detail_match_home_forward
                        }.lparams(width = dip(0), weight = 1F)

                        textView(R.string.forward) {
                            gravity = Gravity.CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), weight = 1F)

                        // away forward
                        textView {
                            id = R.id.detail_match_away_forward
                        }.lparams(width = dip(0), weight = 1F)

                    }.lparams(width = matchParent) {
                        topMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home subs
                        textView {
                            id = R.id.detail_match_home_subs
                        }.lparams(width = dip(0), weight = 1F)

                        textView(R.string.substitutes) {
                            gravity = Gravity.CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), weight = 1F)

                        // away subs
                        textView {
                            id = R.id.detail_match_away_subs
                        }.lparams(width = dip(0), weight = 1F)

                    }.lparams(width = matchParent) {
                        topMargin = dip(10)
                    }
                }
            }
            progressBar {
                id = R.id.progress_Bar
            }.lparams {
                centerHorizontally()
            }
        }


    }
}