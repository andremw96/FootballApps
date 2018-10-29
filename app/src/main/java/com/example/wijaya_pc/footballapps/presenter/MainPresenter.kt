package com.example.wijaya_pc.footballapps.presenter

import android.util.Log
import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.api.TheSportDBApi
import com.example.wijaya_pc.footballapps.coroutine.CoroutineContextProvider
import com.example.wijaya_pc.footballapps.model.LeagueResponse
import com.example.wijaya_pc.footballapps.model.MatchResponse
import com.example.wijaya_pc.footballapps.view.MainView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

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

    fun getLeagueList() {
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getLeague()),
                    LeagueResponse::class.java
                )
            }
            view.showLeagueList(data.await().leagues)
           Log.e("leagues", "$data")
        }
    }
}