package com.kade.dicoding.footballapps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Match(
    @SerializedName("idEvent") var matchId: String?,
    @SerializedName("dateEvent") var matchDate: Date?,
    @SerializedName("strTime") var matchTime: String?,
    @SerializedName("idHomeTeam") var idHomeTeam: String?,
    @SerializedName("idAwayTeam") var idAwayTeam: String?,
    @SerializedName("strHomeTeam") var homeTeam: String?,
    @SerializedName("strAwayTeam") var awayTeam: String?,
    @SerializedName("intHomeScore") var homeScore: String?,
    @SerializedName("intAwayScore") var awayScore: String?,
    @SerializedName("strHomeFormation") var homeFormation: String?,
    @SerializedName("strAwayFormation") var awayFormation: String?,
    @SerializedName("strHomeGoalDetails") var homeGoals: String?,
    @SerializedName("strAwayGoalDetails") var awayGoals: String?,
    @SerializedName("intHomeShots") var homeShots: String?,
    @SerializedName("intAwayShots") var awayShots: String?,
    @SerializedName("strHomeLineupGoalkeeper") var homeGoalKeeper: String?,
    @SerializedName("strAwayLineupGoalkeeper") var awayGoalKeeper: String?,
    @SerializedName("strHomeLineupDefense") var homeDefence: String?,
    @SerializedName("strAwayLineupDefense") var awayDefence: String?,
    @SerializedName("strHomeLineupMidfield") var homeMidfield: String?,
    @SerializedName("strAwayLineupMidfield") var awayMidfield: String?,
    @SerializedName("strHomeLineupForward") var homeForward: String?,
    @SerializedName("strAwayLineupForward") var awayForward: String?,
    @SerializedName("strHomeLineupSubstitutes") var homeSubstitutes: String?,
    @SerializedName("strAwayLineupSubstitutes") var awaySubtitutes: String?
) : Parcelable
