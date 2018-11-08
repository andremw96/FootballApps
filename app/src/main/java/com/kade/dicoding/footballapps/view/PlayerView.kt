package com.kade.dicoding.footballapps.view

import com.kade.dicoding.footballapps.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
}