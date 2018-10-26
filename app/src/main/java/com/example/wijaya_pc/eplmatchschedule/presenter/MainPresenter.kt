package com.example.wijaya_pc.eplmatchschedule.presenter

import com.example.wijaya_pc.eplmatchschedule.api.ApiRepository
import com.example.wijaya_pc.eplmatchschedule.api.TheSportDBApi
import com.example.wijaya_pc.eplmatchschedule.coroutine.CoroutineContextProvider
import com.example.wijaya_pc.eplmatchschedule.model.MatchResponse
import com.example.wijaya_pc.eplmatchschedule.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLast15MatchesList(leagueId: String?) {
        //Log.d("linknya", TheSportDBApi.getLast15Matches(leagueId))
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getLast15Matches(leagueId)),
                    MatchResponse::class.java
                )
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }

    fun getNext15MatchesList(leagueId: String?) {
        //Log.d("linknya", TheSportDBApi.getLast15Matches(leagueId))
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getNext15Matches(leagueId)),
                    MatchResponse::class.java
                )
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }
}