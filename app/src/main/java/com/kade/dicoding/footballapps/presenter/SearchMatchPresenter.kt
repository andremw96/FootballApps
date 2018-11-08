package com.kade.dicoding.footballapps.presenter

import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.api.TheSportDBApi
import com.kade.dicoding.footballapps.coroutine.CoroutineContextProvider
import com.kade.dicoding.footballapps.model.SearchMatchResponse
import com.kade.dicoding.footballapps.view.SearchMatchView
import com.google.gson.Gson
import kotlinx.coroutines.*

class SearchMatchPresenter(
    private val view: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearchMatch(matchName: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = withContext(Dispatchers.IO) {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getSearchMatch(matchName)),
                    SearchMatchResponse::class.java
                )
            }
            view.showSearchMatchList(data.event)
            view.hideLoading()
        }
    }
}