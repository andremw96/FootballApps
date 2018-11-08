package com.kade.dicoding.footballapps.presenter

import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.api.TheSportDBApi
import com.kade.dicoding.footballapps.coroutine.TestContextProvider
import com.kade.dicoding.footballapps.model.Player
import com.kade.dicoding.footballapps.model.PlayerResponse
import com.kade.dicoding.footballapps.view.PlayerView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PlayerPresenterTest {

    @Mock
    private
    lateinit var view: PlayerView

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var presenter: PlayerPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun testGetPlayerTeam() {
        val players: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(players)
        val teamName = "Chelsea"

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getPlayerTeam(teamName)),
                PlayerResponse::class.java
            )
        ).thenReturn(response)

        presenter.getPlayerTeam(teamName)

        verify(view).showLoading()
        verify(view).showPlayerList(players)
        verify(view).hideLoading()

    }
}