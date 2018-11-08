package com.kade.dicoding.footballapps.feature.player

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import com.kade.dicoding.footballapps.R
import com.kade.dicoding.footballapps.R.id.*
import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.invisible
import com.kade.dicoding.footballapps.model.Player
import com.kade.dicoding.footballapps.presenter.DetailPlayerPresenter
import com.kade.dicoding.footballapps.ui.DetailPlayerUI
import com.kade.dicoding.footballapps.view.DetailPlayerView
import com.kade.dicoding.footballapps.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class DetailPlayerActivity : AppCompatActivity(), DetailPlayerView {

    private lateinit var detailView: ScrollView
    private lateinit var progressBar: ProgressBar

    private lateinit var detailPresenter: DetailPlayerPresenter

    private lateinit var playerID: String
    private lateinit var playerName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailPlayerUI().setContentView(this)

        val intent = intent
        playerID = intent.getStringExtra("id")
        playerName = intent.getStringExtra("name")

        supportActionBar?.title = playerName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailView = find(player_detail_sv)
        progressBar = find(player_detail_progress)

        val request = ApiRepository()
        val gson = Gson()
        detailPresenter = DetailPlayerPresenter(this, request, gson)

        detailPresenter.getPlayerDetails(playerID)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerDetail(data: Player) {
        val playerThumb: ImageView = find(player_detail_photo)
        Picasso.get().load(data.playerThumb).placeholder(R.drawable.default_player).into(playerThumb)

        val playerWeight: TextView = find(player_detail_weight)
        playerWeight.text = data.playerWeight

        val playerHeight: TextView = find(player_detail_height)
        playerHeight.text = data.playerHeight

        val playerPosition: TextView = find(player_detail_position)
        playerPosition.text = data.playerPosition

        val playerDesc: TextView = find(player_detail_description)
        playerDesc.text = data.playerDescription
    }

}