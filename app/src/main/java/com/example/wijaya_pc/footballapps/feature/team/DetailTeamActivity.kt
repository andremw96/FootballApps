package com.example.wijaya_pc.footballapps.feature.team

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.wijaya_pc.footballapps.R.id.*
import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.invisible
import com.example.wijaya_pc.footballapps.model.Team
import com.example.wijaya_pc.footballapps.presenter.DetailTeamPresenter
import com.example.wijaya_pc.footballapps.ui.DetailTeamUI
import com.example.wijaya_pc.footballapps.view.DetailTeamView
import com.example.wijaya_pc.footballapps.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.support.v4.onRefresh

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var progressBar : ProgressBar

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var teamDescription: TextView

    private lateinit var presenter: DetailTeamPresenter
    private lateinit var teams: Team
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailTeamUI().setContentView(this)

        swipeRefresh = find(swipeRefreshDetailTeam)
        progressBar = find(progressBarDetailTeam)
        teamBadge = find(team_badgeDetailTeam)
        teamName = find(team_nameDetailTeam)
        teamFormedYear = find(team_formedyearDetailTeam)
        teamStadium = find(team_stadiumDetailTeam)
        teamDescription = find(team_descDetailTeam)


        val intent = intent
        id = intent.getStringExtra("id")

        // nambah toolbar judul dan tombol back
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailTeamPresenter(this, request, gson)
        presenter.getTeamDetail(id)

        swipeRefresh.onRefresh {
            presenter.getTeamDetail(id)
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<Team>) {
        teams = Team(data[0].teamId,
            data[0].teamName,
            data[0].teamBadge)

        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
        teamDescription.text = data[0].teamDescription
        teamFormedYear.text = data[0].teamFormedYear
        teamStadium.text = data[0].teamStadium
    }
}