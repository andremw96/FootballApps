package com.example.wijaya_pc.footballapps.presenter

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.api.TheSportDBApi
import com.example.wijaya_pc.footballapps.coroutine.CoroutineContextProvider
import com.example.wijaya_pc.footballapps.database.database
import com.example.wijaya_pc.footballapps.model.FavoriteMatches
import com.example.wijaya_pc.footballapps.model.MatchResponse
import com.example.wijaya_pc.footballapps.model.TeamResponse
import com.example.wijaya_pc.footballapps.view.DetailView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert


class DetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getMatchDetail(matchID: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getMatchDetail(matchID)),
                    MatchResponse::class.java
                )
            }
            view.getMatch(data.await().events[0])
            view.hideLoading()
        }

    }

    fun getTeamDetail(teamID: String?, homeTeam: Boolean) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getTeam(teamID)),
                    TeamResponse::class.java
                )
            }
            view.getTeam(data.await().teams[0], homeTeam)
            view.hideLoading()
        }
    }

    fun addToFavorite(
        context: Context,
        matchID: String?,
        matchDate: String?,
        matchIDHomeTeam: String?,
        matchIDAwayTeam: String?,
        homeTeam: String?,
        awayTeam: String?,
        homeScore: String?,
        awayScore: String?
    ) {
        try {
            context.database.use {
                insert(
                    FavoriteMatches.TABLE_FAVORITE_MATCH,
                    FavoriteMatches.MATCH_ID to matchID,
                    FavoriteMatches.MATCH_DATE to matchDate,
                    FavoriteMatches.HOME_TEAM_ID to matchIDHomeTeam,
                    FavoriteMatches.AWAY_TEAM_ID to matchIDAwayTeam,
                    FavoriteMatches.HOME_TEAM_NAME to homeTeam,
                    FavoriteMatches.AWAY_TEAM_NAME to awayTeam,
                    FavoriteMatches.HOME_TEAM_SCORE to homeScore,
                    FavoriteMatches.AWAY_TEAM_SCORE to awayScore
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.d("failed insert fav", e.localizedMessage)
        }
    }

    fun removeFromFavorite(context: Context, matchID: String?) {
        try {
            context.database.use {
                delete(FavoriteMatches.TABLE_FAVORITE_MATCH, "(MATCH_ID = {id})", "id" to "$matchID")
            }
        } catch (e: SQLiteConstraintException) {
            Log.d("failed delete fav", e.localizedMessage)
        }
    }
}