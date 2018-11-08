package com.kade.dicoding.footballapps.model

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("idLeague")
    var leagueId: String?,

    @SerializedName("strLeague")
    var leagueName: String?
)