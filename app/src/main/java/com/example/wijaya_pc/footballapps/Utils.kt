package com.example.wijaya_pc.footballapps

import android.annotation.SuppressLint
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

@SuppressLint("SimpleDateFormat")
fun dateToSimpleString(date: Date?): String? = with(date ?: Date()) {
    SimpleDateFormat("EEE, dd MMM yyyy").format(this)
}

@SuppressLint("SimpleDateFormat")
fun toGMTFormat(date: String, time: String): Date? {
    val formatter = SimpleDateFormat("dd/MM/yy HH:mm:ss")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return formatter.parse(dateTime)
}