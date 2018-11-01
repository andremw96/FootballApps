package com.example.wijaya_pc.footballapps.view

import com.example.wijaya_pc.footballapps.model.Match

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchMatchList(data: List<Match>)
}