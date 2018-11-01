package com.example.wijaya_pc.footballapps.presenter

import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.api.TheSportDBApi
import com.example.wijaya_pc.footballapps.coroutine.CoroutineContextProvider
import com.example.wijaya_pc.footballapps.model.TeamResponse
import com.example.wijaya_pc.footballapps.view.SearchTeamView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchTeamPresenter(
    private val view: SearchTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearchTeam(teamName: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getSearchTeam(teamName)),
                    TeamResponse::class.java
                )
            }
            view.showSearchTeamList(data.await().teams)
            view.hideLoading()
        }
    }
}