package com.kade.dicoding.footballapps.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kade.dicoding.footballapps.R.id.team_badge
import com.kade.dicoding.footballapps.R.id.team_name
import com.kade.dicoding.footballapps.model.FavoriteTeams
import com.kade.dicoding.footballapps.ui.TeamUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class FavoriteTeamsAdapter(
    private val favoriteTeams: MutableList<FavoriteTeams>,
    private val listener: (FavoriteTeams) -> Unit
) : RecyclerView.Adapter<FavoriteTeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamsViewHolder {
        return FavoriteTeamsViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = favoriteTeams.size

    override fun onBindViewHolder(holder: FavoriteTeamsViewHolder, position: Int) {
        holder.bindItem(favoriteTeams[position], listener)
    }

}

class FavoriteTeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamBadge: ImageView = view.find(team_badge)
    private val teamName: TextView = view.find(team_name)

    fun bindItem(favoriteTeams: FavoriteTeams, listener: (FavoriteTeams) -> Unit) {
        Picasso.get().load(favoriteTeams.teamBadge).into(teamBadge)
        teamName.text = favoriteTeams.teamName
        itemView.setOnClickListener { listener(favoriteTeams) }
    }
}