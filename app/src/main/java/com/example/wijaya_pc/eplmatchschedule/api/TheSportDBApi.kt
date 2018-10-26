package com.example.wijaya_pc.eplmatchschedule.api

import android.net.Uri
import com.example.wijaya_pc.eplmatchschedule.BuildConfig

object TheSportDBApi {

    fun getLast15Matches(leagueID: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + leagueID
    }

    fun getNext15Matches(leagueID: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + leagueID
    }

    fun getMatchDetail(matchID: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id=" + matchID
    }

    fun getTeam(teamID: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + teamID
    }
}