package com.kade.dicoding.footballapps.presenter

import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.api.TheSportDBApi
import com.kade.dicoding.footballapps.coroutine.CoroutineContextProvider
import com.kade.dicoding.footballapps.model.PlayerDetailResponse
import com.kade.dicoding.footballapps.view.DetailPlayerView
import com.google.gson.Gson
import kotlinx.coroutines.*

class DetailPlayerPresenter(
    private val view: DetailPlayerView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPlayerDetails(playerId: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = withContext(Dispatchers.IO) {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getPlayerDetails(playerId)),
                    PlayerDetailResponse::class.java
                )
            }
            view.showPlayerDetail(data.players[0])
            view.hideLoading()
        }
    }
}