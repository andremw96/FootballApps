package com.example.wijaya_pc.eplmatchschedule.presenter

import com.example.wijaya_pc.eplmatchschedule.api.ApiRepository
import com.example.wijaya_pc.eplmatchschedule.api.TheSportDBApi
import com.example.wijaya_pc.eplmatchschedule.model.MatchResponse
import com.example.wijaya_pc.eplmatchschedule.model.TeamResponse
import com.example.wijaya_pc.eplmatchschedule.view.DetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getMatchDetail(matchID: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getMatchDetail(matchID)),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.getMatch(data.events[0])
            }
        }
    }

    fun getTeamDetail(teamID: String?, homeTeam: Boolean) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeam(teamID)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.getTeam(data.teams[0], homeTeam)
            }
        }

    }
}