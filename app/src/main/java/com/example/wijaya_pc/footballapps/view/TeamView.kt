package com.example.wijaya_pc.footballapps.view

import com.example.wijaya_pc.footballapps.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}