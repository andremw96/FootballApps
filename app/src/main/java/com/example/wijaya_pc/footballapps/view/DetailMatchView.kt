package com.example.wijaya_pc.footballapps.view

import com.example.wijaya_pc.footballapps.model.Match
import com.example.wijaya_pc.footballapps.model.Team

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun getMatch(data: Match)
    fun getTeam(data: Team, homeTeam: Boolean)
}