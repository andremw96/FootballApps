package com.example.wijaya_pc.eplmatchschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Match(
    @SerializedName("idEvent") var matchId: String? = null,

    @SerializedName("dateEvent") var matchDate: Date? = null,

    @SerializedName("idHomeTeam") var idHomeTeam: String? = null,

    @SerializedName("idAwayTeam") var idAwayTeam: String? = null,

    @SerializedName("strHomeTeam") var homeTeam: String? = null,

    @SerializedName("strAwayTeam") var awayTeam: String? = null,

    @SerializedName("intHomeScore") var homeScore: String? = null,

    @SerializedName("intAwayScore") var awayScore: String? = null,

    @SerializedName("strHomeFormation") var homeFormation: String? = null,

    @SerializedName("strAwayFormation") var awayFormation: String? = null,

    @SerializedName("strHomeGoalDetails") var homeGoals: String? = null,

    @SerializedName("strAwayGoalDetails") var awayGoals: String? = null,

    @SerializedName("intHomeShots") var homeShots: String? = null,

    @SerializedName("intAwayShots") var awayShots: String? = null,

    @SerializedName("strHomeLineupGoalkeeper") var homeGoalKeeper: String? = null,

    @SerializedName("strAwayLineupGoalkeeper") var awayGoalKeeper: String? = null,

    @SerializedName("strHomeLineupDefense") var homeDefence: String? = null,

    @SerializedName("strAwayLineupDefense") var awayDefence: String? = null,

    @SerializedName("strHomeLineupMidfield") var homeMidfield: String? = null,

    @SerializedName("strAwayLineupMidfield") var awayMidfield: String? = null,

    @SerializedName("strHomeLineupForward") var homeForward: String? = null,

    @SerializedName("strAwayLineupForward") var awayForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes") var homeSubstitutes: String? = null,

    @SerializedName("strAwayLineupSubstitutes") var awaySubtitutes: String? = null
) : Parcelable
