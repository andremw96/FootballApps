package com.example.wijaya_pc.footballapps.feature.player

import android.content.Context
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
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.adapter.PlayerAdapter
import com.example.wijaya_pc.footballapps.api.ApiRepository
import com.example.wijaya_pc.footballapps.invisible
import com.example.wijaya_pc.footballapps.model.Player
import com.example.wijaya_pc.footballapps.presenter.PlayerPresenter
import com.example.wijaya_pc.footballapps.view.PlayerView
import com.example.wijaya_pc.footballapps.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PlayersFragment : Fragment(), AnkoComponent<Context>, PlayerView {

    companion object {
        private const val ARG_TEAM_NAME = "team_name"

        fun newInstance(teamName: String?): PlayersFragment {
            val fragment = PlayersFragment()
            val args = Bundle()
            args.putString(ARG_TEAM_NAME, teamName)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var listPlayer: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var players: MutableList<Player> = mutableListOf()
    private lateinit var playerPresenter: PlayerPresenter
    private lateinit var playerAdapter: PlayerAdapter

    private lateinit var teamName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        teamName = arguments?.getString(PlayersFragment.ARG_TEAM_NAME).toString()

        val request = ApiRepository()
        val gson = Gson()
        playerPresenter = PlayerPresenter(this, request, gson)

        playerAdapter = PlayerAdapter(players) {
            ctx.startActivity<DetailPlayerActivity>("id" to "${it.playerId}", "name" to "${it.playerName}")
        }
        listPlayer.adapter = playerAdapter

        playerPresenter.getPlayerTeam(teamName)

        swipeRefresh.onRefresh {
            playerPresenter.getPlayerTeam(teamName)
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
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listPlayer = recyclerView {
                        id = R.id.listPlayer
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

    override fun showPlayerList(data: List<Player>) {
        swipeRefresh.isRefreshing = false
        players.clear()
        players.addAll(data)
        playerAdapter.notifyDataSetChanged()
    }

}