package com.example.wijaya_pc.footballapps.model

import com.google.gson.annotations.SerializedName

data class Player (
    @SerializedName("idPlayer")
    var playerId : String?,

    @SerializedName("strCutout")
    var playerPhoto : String?,

    @SerializedName("strPlayer")
    var playerName : String?,

    @SerializedName("strPosition")
    var playerPosition : String?,

    @SerializedName("strFanart1")
    var playerThumb : String?,

    @SerializedName("strWeight")
    var playerWeight : String?,

    @SerializedName("strHeight")
    var playerHeight : String?,

    @SerializedName("strDescriptionEN")
    var playerDescription : String?
)