package com.kade.dicoding.footballapps.presenter

import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.api.TheSportDBApi
import com.kade.dicoding.footballapps.coroutine.TestContextProvider
import com.kade.dicoding.footballapps.model.Match
import com.kade.dicoding.footballapps.model.MatchResponse
import com.kade.dicoding.footballapps.model.Team
import com.kade.dicoding.footballapps.model.TeamResponse
import com.kade.dicoding.footballapps.view.DetailMatchView
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.text.SimpleDateFormat


class DetailMatchPresenterTest {

    @Mock
    private
    lateinit var view: DetailMatchView

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun testGetMatchDetail() {
        val matches: List<Match> = listOf(
            Match(
                "576554",
                SimpleDateFormat("MM/dd/yyyy").parse("2018/10/20"),
                SimpleDateFormat("HH:mm").parse("11:30:00+00:00").toString(),
                "133610",
                "133612",
                "Chelsea",
                "Man United",
                "2",
                "2",
                null,
                null,
                "21':Antonio Ruediger;90':Ross Barkley;",
                "55':Anthony Martial;73':Anthony Martial;",
                "6",
                "4",
                "Kepa Arrizabalaga; ",
                "David De Gea; ",
                "Cesar Azpilicueta; Antonio Ruediger; David Luiz; Marcos Alonso; ",
                "Ashley Young; Chris Smalling; Victor Nilsson Lindeloef; Luke Shaw; ",
                "N'Golo Kante; Jorginho; Mateo Kovacic; ",
                "Nemanja Matic; Paul Pogba; Marcus Rashford; Juan Mata; Anthony Martial; ",
                "Willian; Alvaro Morata; Eden Hazard; ",
                "Romelu Lukaku; ",
                "Wilfredo Caballero; Gary Cahill; Davide Zappacosta; Cesc Fabregas; Pedro Rodriguez; Olivier Giroud; Ross Barkley; ",
                "Sergio Romero; Eric Bailly; Matteo Darmian; Andreas Pereira; Ander Herrera; Fred; Alexis Sanchez; "
            )
        )
        val response = MatchResponse(matches)
        val matchId = "576554"

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getMatchDetail(matchId)),
                MatchResponse::class.java
            )
        ).thenReturn(response)

        presenter.getMatchDetail(matchId)

        verify(view).showLoading()
        verify(view).getMatch(matches[0])
        verify(view).hideLoading()
    }

    @Test
    fun testGetTeamDetail() {
        val teamId = "133610"
        val team: List<Team> = listOf(
            Team(
                "133610",
                "Chelsea",
                "https://www.thesportsdb.com/images/media/team/badge/yvwvtu1448813215.png"
            )
        )
        val response = TeamResponse(team)
        val homeTeam = false

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeam(teamId)),
                TeamResponse::class.java
            )
        ).thenReturn(response)

        presenter.getTeamDetail(teamId, homeTeam)

        verify(view).showLoading()
        verify(view).getTeam(team[0], homeTeam)
        verify(view).hideLoading()

    }
}