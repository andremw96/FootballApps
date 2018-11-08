package com.kade.dicoding.footballapps.view

import com.kade.dicoding.footballapps.model.Match
import com.kade.dicoding.footballapps.model.Team

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun getMatch(data: Match)
    fun getTeam(data: Team, homeTeam: Boolean)
}