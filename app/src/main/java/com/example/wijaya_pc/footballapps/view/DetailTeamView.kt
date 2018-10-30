package com.example.wijaya_pc.footballapps.view

import com.example.wijaya_pc.footballapps.model.Team

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}