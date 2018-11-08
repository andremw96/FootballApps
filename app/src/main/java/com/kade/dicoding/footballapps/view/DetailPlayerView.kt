package com.kade.dicoding.footballapps.view

import com.kade.dicoding.footballapps.model.Player

interface DetailPlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: Player)
}