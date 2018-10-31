package com.example.wijaya_pc.footballapps.view

import com.example.wijaya_pc.footballapps.model.Player

interface DetailPlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: Player)
}