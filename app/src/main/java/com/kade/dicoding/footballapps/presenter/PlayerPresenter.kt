package com.kade.dicoding.footballapps.presenter

import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.api.TheSportDBApi
import com.kade.dicoding.footballapps.coroutine.CoroutineContextProvider
import com.kade.dicoding.footballapps.model.PlayerResponse
import com.kade.dicoding.footballapps.view.PlayerView
import com.google.gson.Gson
import kotlinx.coroutines.*

class PlayerPresenter(
    private val view: PlayerView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPlayerTeam(teamName: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = withContext(Dispatchers.IO) {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getPlayerTeam(teamName)),
                    PlayerResponse::class.java
                )
            }
            view.showPlayerList(data.player)
            view.hideLoading()
        }
    }
}
