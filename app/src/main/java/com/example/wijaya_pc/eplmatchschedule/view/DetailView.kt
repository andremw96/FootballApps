package com.example.wijaya_pc.eplmatchschedule.view

import com.example.wijaya_pc.eplmatchschedule.model.Match
import com.example.wijaya_pc.eplmatchschedule.model.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun getMatch(data: Match)
    fun getTeam(data: Team, homeTeam: Boolean)
    fun addToFavorite()
    fun removeFromFavorite()
    fun setFavorite()
    fun favoriteState()

}