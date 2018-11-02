package com.example.wijaya_pc.footballapps.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.R.id.*
import com.example.wijaya_pc.footballapps.model.Player
import com.example.wijaya_pc.footballapps.ui.PlayerUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk15.coroutines.onClick

class PlayerAdapter(private val players: List<Player>, private val listener: (Player) -> Unit) :
    RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val playerPhoto: ImageView = view.find(player_photo)
    private val playerName: TextView = view.find(player_name)
    private val playerPosition: TextView = view.find(player_position)

    fun bindItem(players: Player, listener: (Player) -> Unit) {
        Picasso.get().load(players.playerPhoto).placeholder(R.drawable.default_player).into(playerPhoto)

        playerName.text = players.playerName
        playerPosition.text = players.playerPosition

        itemView.onClick { listener(players) }
    }
}