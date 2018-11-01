package com.example.wijaya_pc.footballapps.feature.team

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.R.layout.activity_search_team
import com.example.wijaya_pc.footballapps.adapter.TeamAdapter
import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.invisible
import com.example.wijaya_pc.footballapps.model.Team
import com.example.wijaya_pc.footballapps.presenter.SearchMatchPresenter
import com.example.wijaya_pc.footballapps.presenter.SearchTeamPresenter
import com.example.wijaya_pc.footballapps.presenter.TeamPresenter
import com.example.wijaya_pc.footballapps.view.SearchTeamView
import com.example.wijaya_pc.footballapps.view.TeamView
import com.example.wijaya_pc.footballapps.visible
import com.google.gson.Gson
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {

    private lateinit var toolbar: Toolbar

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var swipeRefresh : SwipeRefreshLayout

    private var teams: MutableList<Team>  = mutableListOf()
    private lateinit var teamPresenter: SearchTeamPresenter
    private lateinit var teamAdapter: TeamAdapter

    private lateinit var searchView : SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_search_team)

        toolbar = find(R.id.toolbar_search_team)
        recyclerView = find(R.id.rv_search_team)
        progressBar = find(R.id.progress_bar_search_team)
        swipeRefresh = find(R.id.swipe_refresh_search_team)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Search Team"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        teamPresenter = SearchTeamPresenter(this, request, gson)

        recyclerView.layoutManager = LinearLayoutManager(ctx)
        teamAdapter = TeamAdapter(teams) {
            ctx.startActivity<DetailTeamActivity>("id" to "${it.teamId}", "desc" to "${it.teamDescription}", "name" to "${it.teamName}")
        }
        recyclerView.adapter = teamAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchMenu = menu.findItem(R.id.action_search)
        searchMenu.expandActionView()
        searchView = searchMenu.actionView as SearchView
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                teamPresenter.getSearchTeam(query)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                teamPresenter.getSearchTeam(newText)

                return true
            }

        })

        return true
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

    override fun showSearchTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        teamAdapter.notifyDataSetChanged()
    }
}