package com.example.wijaya_pc.footballapps.model

data class FavoriteTeams(
    val id: Long?,
    val teamId: String?,
    val teamName: String?,
    val teamBadge: String?,
    val teamFormedYear: String?,
    val teamStadium: String?,
    val teamDescription: String?
) {

    companion object {
        const val TABLE_FAVORITE_TEAMS: String = "TABLE_FAVORITE_TEAMS"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_FORMED_YEAR: String = "TEAM_FORMED_YEAR"
        const val TEAM_STADIUM: String = "TEAM_STADIUM"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
    }
}