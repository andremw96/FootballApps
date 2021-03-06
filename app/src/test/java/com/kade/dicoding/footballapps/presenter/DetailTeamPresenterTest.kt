package com.kade.dicoding.footballapps.presenter

import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.api.TheSportDBApi
import com.kade.dicoding.footballapps.coroutine.TestContextProvider
import com.kade.dicoding.footballapps.model.Team
import com.kade.dicoding.footballapps.model.TeamResponse
import com.kade.dicoding.footballapps.view.DetailTeamView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailTeamPresenterTest {

    @Mock
    private
    lateinit var view: DetailTeamView

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var presenter: DetailTeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailTeamPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamDetail() {
        val teams: List<Team> = listOf(
            Team(
                "133604",
                "Arsenal",
                "https://www.thesportsdb.com/images/media/team/badge/vrtrtp1448813175.png"
            )
        )
        val response = TeamResponse(teams)
        val teamId = "133604"

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamDetail(teamId)),
                TeamResponse::class.java
            )
        ).thenReturn(response)

        presenter.getTeamDetail(teamId)

        verify(view).showLoading()
        verify(view).showTeamDetail(teams)
        verify(view).hideLoading()
    }
}