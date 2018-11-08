package com.kade.dicoding.footballapps.presenter

import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.api.TheSportDBApi
import com.kade.dicoding.footballapps.coroutine.CoroutineContextProvider
import com.kade.dicoding.footballapps.model.TeamResponse
import com.kade.dicoding.footballapps.view.SearchTeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchTeamPresenter(
    private val view: SearchTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearchTeam(teamName: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = withContext(Dispatchers.IO) {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getSearchTeam(teamName)),
                    TeamResponse::class.java
                )
            }
            view.showSearchTeamList(data.teams)
            view.hideLoading()
        }
    }
}