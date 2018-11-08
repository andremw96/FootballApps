package com.kade.dicoding.footballapps.view

import com.kade.dicoding.footballapps.model.Team

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showSearchTeamList(data: List<Team>)
}