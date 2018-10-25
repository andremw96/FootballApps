package com.example.wijaya_pc.eplmatchschedule

import android.view.View
import java.text.SimpleDateFormat

//untuk menampilkan ProgressBar
fun View.visible() {
    visibility = View.VISIBLE
}

//invisible() untuk menyembunyikannya
fun View.invisible() {
    visibility = View.INVISIBLE
}

val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy")
