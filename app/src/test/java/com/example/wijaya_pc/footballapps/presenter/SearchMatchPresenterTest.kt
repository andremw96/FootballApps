package com.example.wijaya_pc.footballapps.presenter

import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.api.TheSportDBApi
import com.example.wijaya_pc.footballapps.coroutine.TestContextProvider
import com.example.wijaya_pc.footballapps.model.Match
import com.example.wijaya_pc.footballapps.model.SearchMatchResponse
import com.example.wijaya_pc.footballapps.view.SearchMatchView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchMatchPresenterTest {

    @Mock
    private
    lateinit var view: SearchMatchView

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson


    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetSearchMatch() {
        val matches: MutableList<Match> = mutableListOf()
        val response = SearchMatchResponse(matches)
        val matchName = "Barcelona"

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getSearchMatch(matchName)),
                SearchMatchResponse::class.java
            )
        ).thenReturn(response)

        presenter.getSearchMatch(matchName)

        verify(view).showLoading()
        verify(view).showSearchMatchList(matches)
        verify(view).hideLoading()
    }
}