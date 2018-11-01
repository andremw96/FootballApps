package com.example.wijaya_pc.footballapps.api

import com.example.wijaya_pc.footballapps.BuildConfig

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

    fun getLeague() : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/all_leagues.php?s=Soccer"
    }

    fun getAllTeams(leagueName: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + leagueName
    }

    fun getTeamDetail(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + teamId
    }

    fun getPlayerTeam(teamName: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchplayers.php?t=" + teamName
    }

    fun getPlayerDetails(playerId: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupplayer.php?id=" + playerId
    }

    fun getSearchMatch(matchName: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php?e=" + matchName
    }

    fun getSearchTeam(teamName: String?) : String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchteams.php?t=" + teamName
    }
}