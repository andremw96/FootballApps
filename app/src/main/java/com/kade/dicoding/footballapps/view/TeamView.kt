package com.kade.dicoding.footballapps.view

import com.kade.dicoding.footballapps.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}