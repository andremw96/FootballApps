package com.example.wijaya_pc.footballapps.feature.team

import android.R
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.wijaya_pc.footballapps.R.drawable.ic_add_to_favorites
import com.example.wijaya_pc.footballapps.R.drawable.ic_added_to_favorites
import com.example.wijaya_pc.footballapps.R.id.*
import com.example.wijaya_pc.footballapps.R.menu.detail_menu
import com.example.wijaya_pc.footballapps.adapter.pager.DetailTeamPagerAdapter
import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.database.databaseTeam
import com.example.wijaya_pc.footballapps.feature.player.PlayersFragment
import com.example.wijaya_pc.footballapps.invisible
import com.example.wijaya_pc.footballapps.model.FavoriteTeams
import com.example.wijaya_pc.footballapps.model.Team
import com.example.wijaya_pc.footballapps.presenter.DetailTeamPresenter
import com.example.wijaya_pc.footballapps.ui.DetailTeamUI
import com.example.wijaya_pc.footballapps.view.DetailTeamView
import com.example.wijaya_pc.footballapps.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {

    private lateinit var progressBar : ProgressBar
    private lateinit var linearLayout: LinearLayout
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView

    private lateinit var presenter: DetailTeamPresenter
    private lateinit var teams: Team
    private lateinit var id: String

    private lateinit var teamDescription: String
    private lateinit var mTeamName: String

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var mDetailTeamPagerAdapter: DetailTeamPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailTeamUI().setContentView(this)

        val intent = intent
        id = intent.getStringExtra("id")
        teamDescription = intent.getStringExtra("desc")
        mTeamName = intent.getStringExtra("name")

        castingObjectFromUI()

        // nambah toolbar judul dan tombol back
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favoriteState()

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailTeamPresenter(this, request, gson)
        presenter.getTeamDetail(id)
    }

    private fun castingObjectFromUI() {
        linearLayout = find(detail_team_UI)
        progressBar = find(progressBarDetailTeam)
        teamBadge = find(team_badgeDetailTeam)
        teamName = find(team_nameDetailTeam)
        teamFormedYear = find(team_formedyearDetailTeam)
        teamStadium = find(team_stadiumDetailTeam)

        tabLayout = find(tabs_detail_team)
        viewPager = find(viewpager_detail_team)

        tabLayout.addTab(tabLayout.newTab().setText("Overview"))
        tabLayout.addTab(tabLayout.newTab().setText("Players"))

        mDetailTeamPagerAdapter = DetailTeamPagerAdapter(supportFragmentManager)
        mDetailTeamPagerAdapter.addFrag(OverviewTeamFragment.newInstance(teamDescription), "Overview")
        mDetailTeamPagerAdapter.addFrag(PlayersFragment.newInstance(mTeamName), "Players")

        viewPager.adapter = mDetailTeamPagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

    }

    // membuat menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    // apa yg dilakukan ketika menu diklik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {

                if (isFavorite) {
                    presenter.removeFromFavoriteTeam(ctx, teams.teamId)
                    snackbar(linearLayout, "Removed from FavoriteTeams").show()
                }
                else {
                    presenter.addToFavoriteTeam(ctx,
                        teams.teamId,
                        teams.teamName,
                        teams.teamBadge,
                        teams.teamFormedYear,
                        teams.teamStadium,
                        teams.teamDescription
                    )
                    snackbar(linearLayout, "Added to FavoriteTeams").show()
                }

                isFavorite = !isFavorite
                setFavorite()

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

    override fun showTeamDetail(data: List<Team>) {
        teams = Team(data[0].teamId,
            data[0].teamName,
            data[0].teamBadge,
            data[0].teamFormedYear,
            data[0].teamStadium,
            data[0].teamDescription)

        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
        teamFormedYear.text = data[0].teamFormedYear
        teamStadium.text = data[0].teamStadium

        //teamDescription = data[0].teamDescription.toString()
    }

    // fungsi untuk menandai tim yang favorite
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }


    private fun favoriteState(){
        databaseTeam.use {
            val result = select(FavoriteTeams.TABLE_FAVORITE_TEAMS)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeams>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}