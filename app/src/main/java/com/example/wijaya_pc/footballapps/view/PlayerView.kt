package com.example.wijaya_pc.footballapps.view

import com.example.wijaya_pc.footballapps.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
}