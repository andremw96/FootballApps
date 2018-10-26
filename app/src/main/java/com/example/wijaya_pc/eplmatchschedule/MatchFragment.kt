package com.example.wijaya_pc.eplmatchschedule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.wijaya_pc.eplmatchschedule.R.color.colorAccent
import com.example.wijaya_pc.eplmatchschedule.adapter.MatchAdapter
import com.example.wijaya_pc.eplmatchschedule.api.ApiRepository
import com.example.wijaya_pc.eplmatchschedule.model.Match
import com.example.wijaya_pc.eplmatchschedule.presenter.MainPresenter
import com.example.wijaya_pc.eplmatchschedule.view.MainView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchFragment() : Fragment(), AnkoComponent<Context>, MainView {
    private lateinit var listMatch: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MatchAdapter

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

        listMatch.layoutManager = LinearLayoutManager(ctx)
        adapter = MatchAdapter(matches) {
            ctx.startActivity<DetailActivity>(
                "id" to "${it.matchId}",
                "homeTeamId" to "${it.idHomeTeam}",
                "awayTeamId" to "${it.idAwayTeam}"
            )

           /* val intent = Intent(ctx, DetailActivity::class.java)
            intent.putExtra(DetailActivity.matchParcel, it)
            startActivity(intent)*/
        }
        listMatch.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

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
        adapter.notifyDataSetChanged()
    }
}