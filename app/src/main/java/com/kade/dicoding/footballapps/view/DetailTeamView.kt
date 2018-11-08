package com.kade.dicoding.footballapps.view

import com.kade.dicoding.footballapps.model.Team

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}