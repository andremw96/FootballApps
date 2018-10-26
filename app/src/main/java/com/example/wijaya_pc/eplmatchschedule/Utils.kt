package com.example.wijaya_pc.eplmatchschedule

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

//untuk menampilkan ProgressBar
fun View.visible() {
    visibility = View.VISIBLE
}

//invisible() untuk menyembunyikannya
fun View.invisible() {
    visibility = View.INVISIBLE
}

fun dateToSimpleString(date: Date?): String? = with(date ?: Date()) {
    SimpleDateFormat("EEE, dd MMM yyyy").format(this)
}
