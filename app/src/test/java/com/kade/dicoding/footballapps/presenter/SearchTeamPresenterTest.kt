package com.kade.dicoding.footballapps.presenter

import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.api.TheSportDBApi
import com.kade.dicoding.footballapps.coroutine.TestContextProvider
import com.kade.dicoding.footballapps.model.Team
import com.kade.dicoding.footballapps.model.TeamResponse
import com.kade.dicoding.footballapps.view.SearchTeamView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchTeamPresenterTest {

    @Mock
    private
    lateinit var view: SearchTeamView

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var presenter: SearchTeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchTeamPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetSearchTeam() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val teamName = "Chelsea"

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getSearchTeam(teamName)),
                TeamResponse::class.java
            )
        ).thenReturn(response)

        presenter.getSearchTeam(teamName)

        verify(view).showLoading()
        verify(view).showSearchTeamList(teams)
        verify(view).hideLoading()
    }
}