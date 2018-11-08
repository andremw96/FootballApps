package com.kade.dicoding.footballapps.view

import com.kade.dicoding.footballapps.model.League
import com.kade.dicoding.footballapps.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
    fun showLeagueList(data: List<League>)
}