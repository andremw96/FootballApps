package com.example.wijaya_pc.footballapps.view

import com.example.wijaya_pc.footballapps.model.Team

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showSearchTeamList(data: List<Team>)
}