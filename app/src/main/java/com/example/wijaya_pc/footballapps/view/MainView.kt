package com.example.wijaya_pc.footballapps.view

import com.example.wijaya_pc.footballapps.model.League
import com.example.wijaya_pc.footballapps.model.Match

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
    fun showLeagueList(data: List<League>)
}