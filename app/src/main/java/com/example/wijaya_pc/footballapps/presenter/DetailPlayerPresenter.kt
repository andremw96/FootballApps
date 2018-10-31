package com.example.wijaya_pc.footballapps.presenter

import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.api.TheSportDBApi
import com.example.wijaya_pc.footballapps.coroutine.CoroutineContextProvider
import com.example.wijaya_pc.footballapps.model.PlayerDetailResponse
import com.example.wijaya_pc.footballapps.model.PlayerResponse
import com.example.wijaya_pc.footballapps.view.DetailPlayerView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPlayerPresenter(private val view: DetailPlayerView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPlayerDetails(playerId: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPlayerDetails(playerId)),
                    PlayerDetailResponse::class.java
                )
            }
            view.showPlayerDetail(data.await().players[0])
            view.hideLoading()
        }
    }
}