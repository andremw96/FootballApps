package com.kade.dicoding.footballapps.feature.match

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import com.kade.dicoding.footballapps.*
import com.kade.dicoding.footballapps.R.drawable.ic_add_to_favorites
import com.kade.dicoding.footballapps.R.drawable.ic_added_to_favorites
import com.kade.dicoding.footballapps.R.id.*
import com.kade.dicoding.footballapps.R.menu.detail_menu
import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.database.database
import com.kade.dicoding.footballapps.model.FavoriteMatches
import com.kade.dicoding.footballapps.model.Match
import com.kade.dicoding.footballapps.model.Team
import com.kade.dicoding.footballapps.presenter.DetailMatchPresenter
import com.kade.dicoding.footballapps.ui.DetailMatchUI
import com.kade.dicoding.footballapps.view.DetailMatchView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView


class DetailMatchActivity : AppCompatActivity(), DetailMatchView {
    private lateinit var detailView: ScrollView
    private lateinit var progressBar: ProgressBar

    private lateinit var detailPresenter: DetailMatchPresenter
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
        detailPresenter = DetailMatchPresenter(this, request, gson)

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
                // if (isFavorite) removeFromFavorite() else addToFavorite()

                if (isFavorite) {
                    detailPresenter.removeFromFavorite(ctx, matches.matchId)
                    snackbar(detailView, "Removed from FavoriteMatches").show()
                } else {
                    val matchDateTime =
                        dateTimeToSimpleString(toGMTFormat(dateToSimpleString(matches.matchDate), matches.matchTime))

                    detailPresenter.addToFavorite(
                        ctx, matches.matchId,
                        matchDateTime.substring(0, 16),
                        matchDateTime.substring(17, 22),
                        matches.idHomeTeam,
                        matches.idAwayTeam,
                        matches.homeTeam,
                        matches.awayTeam,
                        matches.homeScore,
                        matches.awayScore
                    )
                    snackbar(detailView, "Added to FavoriteMatches").show()
                }

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
            data.matchTime,
            data.idHomeTeam,
            data.idAwayTeam,
            data.homeTeam,
            data.awayTeam,
            data.homeScore,
            data.awayScore,
            data.homeFormation,
            data.awayFormation,
            data.homeGoals,
            data.awayGoals,
            data.homeShots,
            data.awayShots,
            data.homeGoalKeeper,
            data.awayGoalKeeper,
            data.homeDefence,
            data.awayDefence,
            data.homeMidfield,
            data.awayMidfield,
            data.homeForward,
            data.awayForward,
            data.homeSubstitutes,
            data.awaySubtitutes
        )

        val matchDateTime = dateTimeToSimpleString(toGMTFormat(dateToSimpleString(data.matchDate), data.matchTime))

        val matchDate: TextView = find(detail_match_date)
        matchDate.text = matchDateTime.substring(0, 16)

        val matchTime: TextView = find(detail_match_time)
        matchTime.text = matchDateTime.substring(17, 22)

        val hometeam: TextView = find(detail_match_home_team)
        val awayteam: TextView = find(detail_match_away_team)
        hometeam.text = data.homeTeam
        awayteam.text = data.awayTeam

        val homescore: TextView = find(detail_match_home_score)
        val awayscore: TextView = find(detail_match_away_score)
        homescore.text = data.homeScore
        awayscore.text = data.awayScore

        val homegoals: TextView = find(detail_match_home_goals)
        val awaygoals: TextView = find(detail_match_away_goals)
        homegoals.text = data.homeGoals
        awaygoals.text = data.awayGoals

        val homeshots: TextView = find(detail_match_home_shots)
        val awayshots: TextView = find(detail_match_away_shots)
        homeshots.text = data.homeShots
        awayshots.text = data.awayShots

        val homeformation: TextView = find(detail_match_home_formation)
        val awayformation: TextView = find(detail_match_away_formation)
        homeformation.text = data.homeFormation
        awayformation.text = data.awayFormation

        val homegoalkeeper: TextView = find(detail_match_home_goalkeeper)
        val awaygoalkeeper: TextView = find(detail_match_away_goalkeeper)
        homegoalkeeper.text = data.homeGoalKeeper
        awaygoalkeeper.text = data.awayGoalKeeper

        val homedefense: TextView = find(detail_match_home_defense)
        val awaydefense: TextView = find(detail_match_away_defense)
        homedefense.text = data.homeDefence
        awaydefense.text = data.awayDefence

        val homemidfield: TextView = find(detail_match_home_midfield)
        val awaymidfield: TextView = find(detail_match_away_midfield)
        homemidfield.text = data.homeMidfield
        awaymidfield.text = data.awayMidfield

        val homeforward: TextView = find(detail_match_home_forward)
        val awayforward: TextView = find(detail_match_away_forward)
        homeforward.text = data.homeForward
        awayforward.text = data.awayForward

        val homesubs: TextView = find(detail_match_home_subs)
        val awaysubs: TextView = find(detail_match_away_subs)
        homesubs.text = data.homeSubstitutes
        awaysubs.text = data.awaySubtitutes
    }


    override fun getTeam(data: Team, homeTeam: Boolean) {
        val homelogo: ImageView = find(detail_home_logo)
        val awaylogo: ImageView = find(detail_away_logo)
        Picasso.get().load(data.teamBadge).into(if (homeTeam == true) homelogo else awaylogo)
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
