package com.kade.dicoding.footballapps.presenter

import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.api.TheSportDBApi
import com.kade.dicoding.footballapps.coroutine.CoroutineContextProvider
import com.kade.dicoding.footballapps.model.LeagueResponse
import com.kade.dicoding.footballapps.model.MatchResponse
import com.kade.dicoding.footballapps.view.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchPresenter(
    private val view: MatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLast15MatchesList(leagueId: String?) {
        //Log.d("linknya", TheSportDBApi.getLast15Matches(leagueId))
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = withContext(Dispatchers.IO) {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getLast15Matches(leagueId)),
                    MatchResponse::class.java
                )
            }
            view.showMatchList(data.events)
            view.hideLoading()
        }
    }

    fun getNext15MatchesList(leagueId: String?) {
        //Log.d("linknya", TheSportDBApi.getLast15Matches(leagueId))
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = withContext(Dispatchers.IO) {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getNext15Matches(leagueId)),
                    MatchResponse::class.java
                )
            }
            view.showMatchList(data.events)
            view.hideLoading()
        }
    }

    fun getLeagueList() {
        GlobalScope.launch(context.main) {
            val data = withContext(Dispatchers.IO) {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getLeague()),
                    LeagueResponse::class.java
                )
            }
            view.showLeagueList(data.leagues)
        }
    }
}