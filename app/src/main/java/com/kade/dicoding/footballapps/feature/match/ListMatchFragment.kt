package com.kade.dicoding.footballapps.feature.match

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.kade.dicoding.footballapps.R
import com.kade.dicoding.footballapps.R.array.league
import com.kade.dicoding.footballapps.adapter.MatchAdapter
import com.kade.dicoding.footballapps.api.ApiRepository
import com.kade.dicoding.footballapps.invisible
import com.kade.dicoding.footballapps.model.League
import com.kade.dicoding.footballapps.model.Match
import com.kade.dicoding.footballapps.presenter.MatchPresenter
import com.kade.dicoding.footballapps.view.MatchView
import com.kade.dicoding.footballapps.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class ListMatchFragment : Fragment(), AnkoComponent<Context>, MatchView {

    private lateinit var listMatch: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner

    private lateinit var matchAdapter: MatchAdapter

    private lateinit var presenter: MatchPresenter

    private var matches: MutableList<Match> = mutableListOf()

    private var leagueName: String = ""

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): ListMatchFragment {
            val fragment = ListMatchFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        listMatch.layoutManager = LinearLayoutManager(ctx)
        matchAdapter = MatchAdapter(matches) {
            ctx.startActivity<DetailMatchActivity>(
                "id" to "${it.matchId}",
                "homeTeamId" to "${it.idHomeTeam}",
                "awayTeamId" to "${it.idAwayTeam}"
            )
        }
        listMatch.adapter = matchAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()

                if (arguments?.getInt(ARG_SECTION_NUMBER) == 0) {
                    when (leagueName) {
                        "English Premier League" -> {
                            presenter.getLast15MatchesList("4328")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getLast15MatchesList(
                                    "4328"
                                )
                            }
                        }
                        "English League Championship" -> {
                            presenter.getLast15MatchesList("4329")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getLast15MatchesList(
                                    "4329"
                                )
                            }
                        }
                        "Scottish Premier League" -> {
                            presenter.getLast15MatchesList("4330")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getLast15MatchesList(
                                    "4330"
                                )
                            }
                        }
                        "German Bundesliga" -> {
                            presenter.getLast15MatchesList("4331")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getLast15MatchesList(
                                    "4331"
                                )
                            }
                        }
                        "Italian Serie A" -> {
                            presenter.getLast15MatchesList("4332")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getLast15MatchesList(
                                    "4332"
                                )
                            }
                        }
                        "French Ligue 1" -> {
                            presenter.getLast15MatchesList("4334")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getLast15MatchesList(
                                    "4334"
                                )
                            }
                        }
                        "Spanish La Liga" -> {
                            presenter.getLast15MatchesList("4335")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getLast15MatchesList(
                                    "4335"
                                )
                            }
                        }

                    }
                } else {
                    when (leagueName) {
                        "English Premier League" -> {
                            presenter.getNext15MatchesList("4328")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getNext15MatchesList(
                                    "4328"
                                )
                            }
                        }
                        "English League Championship" -> {
                            presenter.getNext15MatchesList("4329")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getNext15MatchesList(
                                    "4329"
                                )
                            }
                        }
                        "Scottish Premier League" -> {
                            presenter.getNext15MatchesList("4330")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getNext15MatchesList(
                                    "4330"
                                )
                            }
                        }
                        "German Bundesliga" -> {
                            presenter.getNext15MatchesList("4331")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getNext15MatchesList(
                                    "4331"
                                )
                            }
                        }
                        "Italian Serie A" -> {
                            presenter.getNext15MatchesList("4332")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getNext15MatchesList(
                                    "4332"
                                )
                            }
                        }
                        "French Ligue 1" -> {
                            presenter.getNext15MatchesList("4334")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getNext15MatchesList(
                                    "4334"
                                )
                            }
                        }
                        "Spanish La Liga" -> {
                            presenter.getNext15MatchesList("4335")

                            swipeRefresh.setOnRefreshListener {
                                presenter.getNext15MatchesList(
                                    "4335"
                                )
                            }
                        }
                    }
                }

        }

            override fun onNothingSelected(parent: AdapterView<*>) {}
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

            spinner = spinner {
                id = R.id.spinner_match
            }

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listMatch = recyclerView {
                        id = R.id.listMatch
                        tag = if (arguments?.getInt(ARG_SECTION_NUMBER) == 0) {
                            "Last_Match"
                        } else {
                            "Next_Match"
                        }
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
        /*leagues.clear()
        leagues.addAll(data)*/
    }
}
