package com.kade.dicoding.footballapps.view

import com.kade.dicoding.footballapps.model.Match

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchMatchList(data: List<Match>)
}