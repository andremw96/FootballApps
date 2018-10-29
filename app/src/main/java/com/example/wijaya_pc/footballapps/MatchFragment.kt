package com.example.wijaya_pc.footballapps

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.wijaya_pc.footballapps.R.color.colorAccent
import com.example.wijaya_pc.footballapps.adapter.MatchAdapter
import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.model.League
import com.example.wijaya_pc.footballapps.model.Match
import com.example.wijaya_pc.footballapps.presenter.MainPresenter
import com.example.wijaya_pc.footballapps.view.MainView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabItem
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchFragment() : Fragment(), AnkoComponent<Context>, MainView {
    private lateinit var listMatch: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner

    private var matches: MutableList<Match> = mutableListOf()
    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var matchAdapter: MatchAdapter

    companion object {
        private const val leagueID = "4328"
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): MatchFragment {
            val fragment = MatchFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /*val spinnerItems = leagues
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter*/

        listMatch.layoutManager = LinearLayoutManager(ctx)
        matchAdapter = MatchAdapter(matches) {
            ctx.startActivity<DetailActivity>(
                "id" to "${it.matchId}",
                "homeTeamId" to "${it.idHomeTeam}",
                "awayTeamId" to "${it.idAwayTeam}"
            )
        }
        listMatch.adapter = matchAdapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        presenter.getLeagueList()


        if (arguments?.getInt(ARG_SECTION_NUMBER) == 0) {
            presenter.getLast15MatchesList(leagueID)

            swipeRefresh.onRefresh {
                presenter.getLast15MatchesList(leagueID)
            }
        } else {
            presenter.getNext15MatchesList(leagueID)

            swipeRefresh.onRefresh {
                presenter.getNext15MatchesList(leagueID)
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            tabLayout {
                id = R.id.tab_match
                lparams(width = matchParent)

                tabItem {

                }.lparams(width = wrapContent, height = wrapContent)

                tabItem {

                }.lparams(width = wrapContent, height = wrapContent)
            }

            spinner = spinner()

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listMatch = recyclerView {
                        id = R.id.listMatch
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        matchAdapter.notifyDataSetChanged()
    }

    override fun showLeagueList(data: List<League>) {
        leagues.clear()
        leagues.addAll(data)

       // val leagueAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, leagues)
    }
}