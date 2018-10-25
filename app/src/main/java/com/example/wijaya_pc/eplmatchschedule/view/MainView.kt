package com.example.wijaya_pc.eplmatchschedule.view

import com.example.wijaya_pc.eplmatchschedule.model.Match

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}