package com.example.wijaya_pc.footballapps.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.wijaya_pc.footballapps.R.id.*
import com.example.wijaya_pc.footballapps.invisible
import com.example.wijaya_pc.footballapps.model.FavoriteMatches
import com.example.wijaya_pc.footballapps.ui.MatchUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.w3c.dom.Text

class FavoriteMatchesAdapter(
    private val favorite: MutableList<FavoriteMatches>,
    private val listener: (FavoriteMatches) -> Unit
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val matchDate: TextView = view.find(match_date)
    private val matchTime: TextView = view.find(match_time)
    private val homeTeamName: TextView = view.find(match_home_team)
    private val homeTeamScore: TextView = view.find(match_home_score)
    private val awayTeamScore: TextView = view.find(match_away_score)
    private val awayTeamName: TextView = view.find(match_away_team)

    private val btnToCalendar : ImageButton = view.find(btn_to_calendar)


    fun bindItem(favorite: FavoriteMatches, listener: (FavoriteMatches) -> Unit) {

        if(!(favorite.homeTeamScore.isNullOrBlank()) && !(favorite.awayTeamScore.isNullOrBlank())) {
            btnToCalendar.invisible()
        }

        matchDate.text = favorite.matchDate
        matchTime.text = favorite.matchTime
        homeTeamName.text = favorite.homeTeamName
        homeTeamScore.text = favorite.homeTeamScore
        awayTeamScore.text = favorite.awayTeamScore
        awayTeamName.text = favorite.awayTeamName


        itemView.onClick { listener(favorite) }
    }
}