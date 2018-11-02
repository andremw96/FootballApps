package com.example.wijaya_pc.footballapps.ui

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.feature.player.DetailPlayerActivity
import org.jetbrains.anko.*

class DetailPlayerUI : AnkoComponent<DetailPlayerActivity> {
    override fun createView(ui: AnkoContext<DetailPlayerActivity>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            imageView {
                id = R.id.player_detail_photo
                scaleType = ImageView.ScaleType.FIT_XY
            }.lparams(width = matchParent, height = dip(250))

            linearLayout {
                lparams(width = matchParent) {
                    topMargin = dip(5)
                    bottomMargin = dip(10)
                }

                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER

                    textView {
                        text = context.getString(R.string.weight)
                        gravity = Gravity.CENTER
                    }

                    textView {
                        id = R.id.player_detail_weight
                        textSize = 30f
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        gravity = Gravity.CENTER
                    }
                }.lparams(weight = 1f)

                linearLayout {
                    orientation = LinearLayout.VERTICAL

                    textView {
                        text = context.getString(R.string.height)
                        gravity = Gravity.CENTER
                    }

                    textView {
                        id = R.id.player_detail_height
                        textSize = 30f
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        gravity = Gravity.CENTER
                    }
                }.lparams(weight = 1f)
            }

            textView {
                id = R.id.player_detail_position
                gravity = Gravity.CENTER
                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
            }

            scrollView {
                id = R.id.player_detail_sv
                lparams(width = matchParent)

                verticalLayout {
                    lparams(width = matchParent)

                    // line separator
                    view {
                        backgroundResource = android.R.color.darker_gray
                    }.lparams(width = matchParent, height = dip(1)) {
                        bottomMargin = dip(10)
                    }

                    textView {
                        id = R.id.player_detail_description
                        padding = dip(8)
                    }
                }
            }
            progressBar {
                id = R.id.player_detail_progress
            }.lparams {

            }
        }
    }
}