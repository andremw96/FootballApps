package com.example.wijaya_pc.footballapps.model

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("idLeague")
    var leagueId: String?,

    @SerializedName("strLeague")
    var leagueName: String?
)