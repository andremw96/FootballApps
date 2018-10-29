package com.example.wijaya_pc.footballapps.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wijaya_pc.footballapps.R.id.*
import com.example.wijaya_pc.footballapps.dateToSimpleString
import com.example.wijaya_pc.footballapps.model.Match
import com.example.wijaya_pc.footballapps.ui.MatchUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class MatchAdapter(
    private val matches: List<Match>,
    private val listener: (Match) -> Unit
) : RecyclerView.Adapter<MatchViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }
}

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val matchDate: TextView = view.find(match_date)
    private val matchHomeTeam: TextView = view.find(match_home_team)
    private val matchHomeScore: TextView = view.find(match_home_score)
    private val matchAwayScore: TextView = view.find(match_away_score)
    private val matchAwayTeam: TextView = view.find(match_away_team)

    fun bindItem(matches: Match, listener: (Match) -> Unit) {
        matchDate.text = dateToSimpleString(matches.matchDate)

        matchHomeTeam.text = matches.homeTeam
        matchHomeScore.text = matches.homeScore

        matchAwayScore.text = matches.awayScore
        matchAwayTeam.text = matches.awayTeam

        itemView.setOnClickListener {
            listener(matches)
        }
    }
}