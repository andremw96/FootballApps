package com.example.wijaya_pc.eplmatchschedule

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import com.example.wijaya_pc.eplmatchschedule.R.drawable.ic_add_to_favorites
import com.example.wijaya_pc.eplmatchschedule.R.drawable.ic_added_to_favorites
import com.example.wijaya_pc.eplmatchschedule.R.id.*
import com.example.wijaya_pc.eplmatchschedule.R.menu.detail_menu
import com.example.wijaya_pc.eplmatchschedule.api.ApiRepository
import com.example.wijaya_pc.eplmatchschedule.database.database
import com.example.wijaya_pc.eplmatchschedule.model.FavoriteMatches
import com.example.wijaya_pc.eplmatchschedule.model.Match
import com.example.wijaya_pc.eplmatchschedule.model.Team
import com.example.wijaya_pc.eplmatchschedule.presenter.DetailPresenter
import com.example.wijaya_pc.eplmatchschedule.ui.DetailMatchUI
import com.example.wijaya_pc.eplmatchschedule.view.DetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import java.text.SimpleDateFormat


class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var detailView: ScrollView
    private lateinit var progressBar: ProgressBar

    private lateinit var detailPresenter: DetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var matches: Match
    private lateinit var matchID: String
    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailMatchUI().setContentView(this)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailView = find(detail_view)
        progressBar = find(progress_Bar)

        val intent = intent
        matchID = intent.getStringExtra("id")
        idHomeTeam = intent.getStringExtra("homeTeamId")
        idAwayTeam = intent.getStringExtra("awayTeamId")


        val request = ApiRepository()
        val gson = Gson()
        detailPresenter = DetailPresenter(this, request, gson)

        detailPresenter.getMatchDetail(matchID)

        detailPresenter.getTeamDetail(idHomeTeam, true)
        detailPresenter.getTeamDetail(idAwayTeam, false)

        //cek apakah match tersebut sudah favorit
        favoriteState()
    }

    //membuat menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun getMatch(data: Match) {
        matches = Match(
            data.matchId,
            data.matchDate,
            data.idHomeTeam,
            data.idAwayTeam,
            data.homeTeam,
            data.awayTeam,
            data.homeScore,
            data.awayScore
        )

        val matchDate: TextView = find(match_date)
        matchDate.text = (SimpleDateFormat("EEE, dd MMM yyyy").format(data.matchDate)).toString()

        val hometeam: TextView = find(match_home_team)
        val awayteam: TextView = find(match_away_team)
        hometeam.text = data.homeTeam
        awayteam.text = data.awayTeam

        val homescore: TextView = find(match_home_score)
        val awayscore: TextView = find(match_away_score)
        homescore.text = data.homeScore
        awayscore.text = data.awayScore

        val homegoals: TextView = find(match_home_goals)
        val awaygoals: TextView = find(match_away_goals)
        homegoals.text = data.homeGoals
        awaygoals.text = data.awayGoals

        val homeshots: TextView = find(match_home_shots)
        val awayshots: TextView = find(match_away_shots)
        homeshots.text = data.homeShots
        awayshots.text = data.awayShots

        val homeformation: TextView = find(match_home_formation)
        val awayformation: TextView = find(match_away_formation)
        homeformation.text = data.homeFormation
        awayformation.text = data.awayFormation

        val homegoalkeeper: TextView = find(match_home_goalkeeper)
        val awaygoalkeeper: TextView = find(match_away_goalkeeper)
        homegoalkeeper.text = data.homeGoalKeeper
        awaygoalkeeper.text = data.awayGoalKeeper

        val homedefense: TextView = find(match_home_defense)
        val awaydefense: TextView = find(match_away_defense)
        homedefense.text = data.homeDefence
        awaydefense.text = data.awayDefence

        val homemidfield: TextView = find(match_home_midfield)
        val awaymidfield: TextView = find(match_away_midfield)
        homemidfield.text = data.homeMidfield
        awaymidfield.text = data.awayMidfield

        val homeforward: TextView = find(match_home_forward)
        val awayforward: TextView = find(match_away_forward)
        homeforward.text = data.homeForward
        awayforward.text = data.awayForward

        val homesubs: TextView = find(match_home_subs)
        val awaysubs: TextView = find(match_away_subs)
        homesubs.text = data.homeSubstitutes
        awaysubs.text = data.awaySubtitutes
    }


    override fun getTeam(data: Team, homeTeam: Boolean) {
        val homelogo: ImageView = find(home_logo)
        val awaylogo: ImageView = find(away_logo)
        Picasso.get().load(data.teamBadge).into(if (homeTeam == true) homelogo else awaylogo)
    }

    private fun addToFavorite() {
        val date = (SimpleDateFormat("EEE, dd MMM yyyy").format(matches.matchDate)).toString()

        try {
            database.use {
                insert(
                    FavoriteMatches.TABLE_FAVORITE_MATCH,
                    FavoriteMatches.MATCH_ID to matches.matchId,
                    FavoriteMatches.MATCH_DATE to date,
                    FavoriteMatches.HOME_TEAM_ID to matches.idHomeTeam,
                    FavoriteMatches.AWAY_TEAM_ID to matches.idAwayTeam,
                    FavoriteMatches.HOME_TEAM_NAME to matches.homeTeam,
                    FavoriteMatches.AWAY_TEAM_NAME to matches.awayTeam,
                    FavoriteMatches.HOME_TEAM_SCORE to matches.homeScore,
                    FavoriteMatches.AWAY_TEAM_SCORE to matches.awayScore
                )
            }
            snackbar(detailView, "Added to FavoriteMatches").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(detailView, e.localizedMessage).show()
        }
    }

    //fungsi untuk menghapus favorite
    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavoriteMatches.TABLE_FAVORITE_MATCH, "(MATCH_ID = {id})", "id" to matchID)
            }
            snackbar(detailView, "Removed from FavoriteMatches").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(detailView, e.localizedMessage).show()
        }
    }

    // fungsi untuk menandai match yang favorite
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }


    private fun favoriteState() {
        database.use {
            val result = select(FavoriteMatches.TABLE_FAVORITE_MATCH)
                .whereArgs(
                    "(MATCH_ID = {id})",
                    "id" to matchID
                )
            val favorite = result.parseList(classParser<FavoriteMatches>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }
}
