package com.example.wijaya_pc.eplmatchschedule.presenter

import com.example.wijaya_pc.eplmatchschedule.api.ApiRepository
import com.example.wijaya_pc.eplmatchschedule.api.TheSportDBApi
import com.example.wijaya_pc.eplmatchschedule.coroutine.TestContextProvider
import com.example.wijaya_pc.eplmatchschedule.model.Match
import com.example.wijaya_pc.eplmatchschedule.model.MatchResponse
import com.example.wijaya_pc.eplmatchschedule.view.MainView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock
    private
    lateinit var view: MainView

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson


    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetLast15MatchesList() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)
        val leagueId = "4328"

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getLast15Matches(leagueId)),
                MatchResponse::class.java
            )
        ).thenReturn(response)

        presenter.getLast15MatchesList(leagueId)

        verify(view).showLoading()
        verify(view).showMatchList(matches)
        verify(view).hideLoading()

    }

    @Test
    fun testGetNext15MatchesList() {
        val matches: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)
        val leagueId = "4328"

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getNext15Matches(leagueId)),
                MatchResponse::class.java
            )
        ).thenReturn(response)

        presenter.getNext15MatchesList(leagueId)

        verify(view).showLoading()
        verify(view).showMatchList(matches)
        verify(view).hideLoading()
    }
}