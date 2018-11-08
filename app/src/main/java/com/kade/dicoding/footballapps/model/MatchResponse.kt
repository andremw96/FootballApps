package com.kade.dicoding.footballapps.model

data class MatchResponse(
    val events: List<Match>
)

data class SearchMatchResponse(
    val event: List<Match>
)