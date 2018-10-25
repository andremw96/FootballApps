package com.example.wijaya_pc.eplmatchschedule.ui

import android.graphics.Typeface
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.wijaya_pc.eplmatchschedule.DetailActivity
import com.example.wijaya_pc.eplmatchschedule.R
import org.jetbrains.anko.*

class DetailMatchUI : AnkoComponent<DetailActivity> {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun createView(ui: AnkoContext<DetailActivity>): View = with(ui) {

        relativeLayout {
            lparams(width = matchParent, height = wrapContent)

            scrollView {
                id = R.id.detail_view
                lparams(width = matchParent, height = wrapContent)

                verticalLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)

                    //match date
                    textView {
                        id = R.id.match_date
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(width = matchParent, height = wrapContent)

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            // home
                            imageView {
                                id = R.id.home_logo
                                setImageResource(R.drawable.img_default)
                            }.lparams(width = matchParent, height = dip(75))

                            textView(R.string.home_team) {
                                id = R.id.match_home_team
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                            }.lparams(width = matchParent, height = matchParent)

                        }.lparams(width = dip(100), height = dip(100)) {
                            weight = 1F
                        }

                        // scoring
                        textView {
                            id = R.id.match_home_score
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent, height = wrapContent) {
                            gravity = Gravity.CENTER_VERTICAL
                            weight = 1F
                        }

                        textView(R.string.vs_string) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent, height = wrapContent) {
                            gravity = Gravity.CENTER_VERTICAL
                            weight = 1F
                        }

                        textView {
                            id = R.id.match_away_score
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams(width = wrapContent, height = wrapContent) {
                            gravity = Gravity.CENTER_VERTICAL
                            weight = 1F
                        }

                        linearLayout {
                            orientation = LinearLayout.VERTICAL

                            //away
                            imageView {
                                id = R.id.away_logo
                                setImageResource(R.drawable.img_default)
                            }.lparams(width = matchParent, height = dip(75))

                            textView(R.string.away_team) {
                                id = R.id.match_away_team
                                textAlignment = View.TEXT_ALIGNMENT_CENTER
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                            }.lparams(width = matchParent, height = matchParent)

                        }.lparams(width = dip(100), height = dip(100)) {
                            weight = 1F
                        }

                    }.lparams(width = matchParent, height = wrapContent)

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
                            id = R.id.match_home_goals
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        textView(R.string.goals) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        // away team goals
                        textView {
                            id = R.id.match_away_goals
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    }.lparams(width = matchParent, height = wrapContent)

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home shots
                        textView {
                            id = R.id.match_home_shots
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        textView(R.string.shots) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        // away shots
                        textView {
                            id = R.id.match_away_shots
                            gravity = Gravity.RIGHT
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    }.lparams(width = matchParent, height = wrapContent) {
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
                            id = R.id.match_home_formation
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        //Lineups String
                        textView(R.string.lineups) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        // away formation
                        textView {
                            id = R.id.match_away_formation
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    }.lparams(width = matchParent, height = wrapContent) {
                        topMargin = dip(10)
                    }


                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home goalkeeper
                        textView {
                            id = R.id.match_home_goalkeeper
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        textView(R.string.goal_keeper) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        // away goalkeeper
                        textView {
                            id = R.id.match_away_goalkeeper
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    }.lparams(width = matchParent, height = wrapContent) {
                        topMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home defense
                        textView {
                            id = R.id.match_home_defense
                        }.lparams(width = dip(0), height = wrapContent, weight = 1f)

                        textView(R.string.defense) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        // away defense
                        textView {
                            id = R.id.match_away_defense
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    }.lparams(width = matchParent, height = wrapContent) {
                        topMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home midfield
                        textView {
                            id = R.id.match_home_midfield
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        textView(R.string.midfield) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        // away midfield
                        textView {
                            id = R.id.match_away_midfield
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    }.lparams(width = matchParent, height = wrapContent) {
                        topMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home forward
                        textView {
                            id = R.id.match_home_forward
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        textView(R.string.forward) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        // away forward
                        textView {
                            id = R.id.match_away_forward
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    }.lparams(width = matchParent, height = wrapContent) {
                        topMargin = dip(10)
                    }

                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL

                        // home subs
                        textView {
                            id = R.id.match_home_subs
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        textView(R.string.substitutes) {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                        // away subs
                        textView {
                            id = R.id.match_away_subs
                        }.lparams(width = dip(0), height = wrapContent, weight = 1F)

                    }.lparams(width = matchParent, height = wrapContent) {
                        topMargin = dip(10)
                    }
                }
            }
            progressBar {
                id = R.id.progress_Bar
            }.lparams{
                centerHorizontally()
            }
        }


    }
}