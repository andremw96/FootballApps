package com.example.wijaya_pc.footballapps.presenter

import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.api.TheSportDBApi
import com.example.wijaya_pc.footballapps.coroutine.TestContextProvider
import com.example.wijaya_pc.footballapps.model.Player
import com.example.wijaya_pc.footballapps.model.PlayerResponse
import com.example.wijaya_pc.footballapps.view.PlayerView
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