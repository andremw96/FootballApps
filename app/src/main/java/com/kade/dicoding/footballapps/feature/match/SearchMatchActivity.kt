package com.kade.dicoding.footballapps.feature.match

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.kade.dicoding.footballapps.R
import com.kade.dicoding.footballapps.R.menu.search_menu
import com.kade.dicoding.footballapps.adapter.MatchAdapter
import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.invisible
import com.kade.dicoding.footballapps.model.Match
import com.kade.dicoding.footballapps.presenter.SearchMatchPresenter
import com.kade.dicoding.footballapps.view.SearchMatchView
import com.kade.dicoding.footballapps.visible
import com.google.gson.Gson
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {
    private lateinit var toolbar: Toolbar

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var matches: MutableList<Match> = mutableListOf()

    private lateinit var matchAdapter: MatchAdapter

    private lateinit var presenter: SearchMatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        toolbar = find(R.id.toolbar_search)
        recyclerView = find(R.id.rv_search)
        progressBar = find(R.id.progress_bar_search)
        swipeRefresh = find(R.id.swipe_refresh_search)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Search Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, request, gson)

        recyclerView.layoutManager = LinearLayoutManager(ctx)
        matchAdapter = MatchAdapter(matches) {
            ctx.startActivity<DetailMatchActivity>(
                "id" to "${it.matchId}",
                "homeTeamId" to "${it.idHomeTeam}",
                "awayTeamId" to "${it.idAwayTeam}"
            )
        }
        recyclerView.adapter = matchAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(search_menu, menu)

        val searchMenu = menu.findItem(R.id.action_search)
        searchMenu.expandActionView()
        searchView = searchMenu.actionView as SearchView
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getSearchMatch(query)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.getSearchMatch(newText)

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

    override fun showSearchMatchList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        matchAdapter.notifyDataSetChanged()
    }

}
