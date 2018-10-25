package com.example.wijaya_pc.eplmatchschedule.presenter

import com.example.wijaya_pc.eplmatchschedule.api.ApiRepository
import com.example.wijaya_pc.eplmatchschedule.api.TheSportDBApi
import com.example.wijaya_pc.eplmatchschedule.model.MatchResponse
import com.example.wijaya_pc.eplmatchschedule.view.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getLast15MatchesList(leagueId: String?) {
        //Log.d("linknya", TheSportDBApi.getLast15Matches(leagueId))
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getLast15Matches(leagueId)),
                MatchResponse::class.java
            )
            // Log.e("the data", "$data")

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

    fun getNext15MatchesList(leagueId: String?) {
        //Log.d("linknya", TheSportDBApi.getLast15Matches(leagueId))
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getNext15Matches(leagueId)),
                MatchResponse::class.java
            )
            // Log.e("the data", "$data")

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }
}