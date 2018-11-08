package com.kade.dicoding.footballapps.presenter

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.api.TheSportDBApi
import com.kade.dicoding.footballapps.coroutine.CoroutineContextProvider
import com.kade.dicoding.footballapps.database.databaseTeam
import com.kade.dicoding.footballapps.model.FavoriteTeams
import com.kade.dicoding.footballapps.model.TeamResponse
import com.kade.dicoding.footballapps.view.DetailTeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert

class DetailTeamPresenter(
    private val view: DetailTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = withContext(Dispatchers.IO) {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                    TeamResponse::class.java
                )
            }
            view.showTeamDetail(data.teams)
            view.hideLoading()
        }
    }

    fun removeFromFavoriteTeam(context: Context, teamId: String?) {
        try {
            context.databaseTeam.use {
                delete(FavoriteTeams.TABLE_FAVORITE_TEAMS, "(TEAM_ID = {id})", "id" to "$teamId")
            }
        } catch (e: SQLiteConstraintException) {
            Log.d("failed delete fav", e.localizedMessage)
        }
    }

    fun addToFavoriteTeam(
        context: Context,
        teamId: String?,
        teamName: String?,
        teamBadge: String?,
        teamFormedYear: String?,
        teamStadium: String?,
        teamDescription: String?
    ) {
        try {
            context.databaseTeam.use {
                insert(
                    FavoriteTeams.TABLE_FAVORITE_TEAMS,
                    FavoriteTeams.TEAM_ID to teamId,
                    FavoriteTeams.TEAM_NAME to teamName,
                    FavoriteTeams.TEAM_BADGE to teamBadge,
                    FavoriteTeams.TEAM_FORMED_YEAR to teamFormedYear,
                    FavoriteTeams.TEAM_STADIUM to teamStadium,
                    FavoriteTeams.TEAM_DESCRIPTION to teamDescription
                )
            }
        } catch (e: SQLiteConstraintException) {
            Log.d("failed add fav", e.localizedMessage)
        }
    }

}