package com.example.wijaya_pc.footballapps.presenter

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.api.TheSportDBApi
import com.example.wijaya_pc.footballapps.coroutine.CoroutineContextProvider
import com.example.wijaya_pc.footballapps.database.databaseTeam
import com.example.wijaya_pc.footballapps.feature.favoritematch.ListFavoriteFragment
import com.example.wijaya_pc.footballapps.model.FavoriteTeams
import com.example.wijaya_pc.footballapps.model.TeamResponse
import com.example.wijaya_pc.footballapps.view.DetailTeamView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert

class DetailTeamPresenter(private val view: DetailTeamView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()
)
{

    fun getTeamDetail(teamId: String) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                    TeamResponse::class.java
                )
            }
            view.showTeamDetail(data.await().teams)
            view.hideLoading()
        }
    }

    fun removeFromFavoriteTeam(context: Context, teamId: String?) {
        try {
            context.databaseTeam.use {
                delete(FavoriteTeams.TABLE_FAVORITE_TEAMS, "(TEAM_ID = {id})", "id" to "$teamId")
            }
        } catch (e: SQLiteConstraintException){
            Log.d("failed delete fav", e.localizedMessage)
        }
    }

    fun addToFavoriteTeam(context: Context, teamId: String?, teamName: String?, teamBadge: String?, teamFormedYear: String?, teamStadium: String?, teamDescription: String?) {
        try {
            context.databaseTeam.use {
                insert(FavoriteTeams.TABLE_FAVORITE_TEAMS,
                    FavoriteTeams.TEAM_ID to teamId,
                    FavoriteTeams.TEAM_NAME to teamName,
                    FavoriteTeams.TEAM_BADGE to teamBadge,
                    FavoriteTeams.TEAM_FORMED_YEAR to teamFormedYear,
                    FavoriteTeams.TEAM_STADIUM to teamStadium,
                    FavoriteTeams.TEAM_DESCRIPTION to teamDescription)
            }
        } catch (e: SQLiteConstraintException){
            Log.d("failed add fav", e.localizedMessage)
        }
    }

}