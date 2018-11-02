package com.example.wijaya_pc.footballapps.feature.favorite

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wijaya_pc.footballapps.R
import com.example.wijaya_pc.footballapps.R.color.colorAccent
import com.example.wijaya_pc.footballapps.adapter.FavoriteMatchesAdapter
import com.example.wijaya_pc.footballapps.adapter.FavoriteTeamsAdapter
import com.example.wijaya_pc.footballapps.database.database
import com.example.wijaya_pc.footballapps.database.databaseTeam
import com.example.wijaya_pc.footballapps.feature.match.DetailMatchActivity
import com.example.wijaya_pc.footballapps.feature.team.DetailTeamActivity
import com.example.wijaya_pc.footballapps.model.FavoriteMatches
import com.example.wijaya_pc.footballapps.model.FavoriteTeams
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class ListFavoriteFragment : Fragment(), AnkoComponent<Context> {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listFav: RecyclerView

    private var favoritesMatches: MutableList<FavoriteMatches> = mutableListOf()
    private var favoritesTeams: MutableList<FavoriteTeams> = mutableListOf()

    private lateinit var favoriteMatchesAdapter: FavoriteMatchesAdapter
    private lateinit var favoriteTeamsAdapter: FavoriteTeamsAdapter

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): ListFavoriteFragment {
            val fragment = ListFavoriteFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments?.getInt(ListFavoriteFragment.ARG_SECTION_NUMBER) == 0) {
            favoriteMatchesAdapter = FavoriteMatchesAdapter(favoritesMatches) {
                ctx.startActivity<DetailMatchActivity>(
                    "id" to "${it.matchId}",
                    "homeTeamId" to "${it.homeTeamId}",
                    "awayTeamId" to "${it.awayTeamId}"
                )
            }

            listFav.adapter = favoriteMatchesAdapter
            showFavoriteMatch()
            swipeRefresh.onRefresh {
                favoritesMatches.clear()
                showFavoriteMatch()
            }
        } else {
            favoriteTeamsAdapter = FavoriteTeamsAdapter(favoritesTeams) {
                ctx.startActivity<DetailTeamActivity>(
                    "id" to "${it.teamId}",
                    "desc" to "${it.teamDescription}",
                    "name" to "${it.teamName}"
                )
            }

            listFav.adapter = favoriteTeamsAdapter
            showFavoriteTeam()
            swipeRefresh.onRefresh {
                favoritesTeams.clear()
                showFavoriteTeam()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
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

                    listFav = recyclerView {
                        id = R.id.listFav
                        if (arguments?.getInt(ListFavoriteFragment.ARG_SECTION_NUMBER) == 0) {
                            tag = "fav_match"
                        }
                        else {
                            tag = "fav_team"
                        }
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }

    private fun showFavoriteMatch() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteMatches.TABLE_FAVORITE_MATCH)
            var favoriteMatches = result.parseList(classParser<FavoriteMatches>())
            favoritesMatches.addAll(favoriteMatches)
            favoriteMatchesAdapter.notifyDataSetChanged()
        }
    }

    private fun showFavoriteTeam() {
        context?.databaseTeam?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteTeams.TABLE_FAVORITE_TEAMS)
            var favoriteTeam = result.parseList(classParser<FavoriteTeams>())
            favoritesTeams.addAll(favoriteTeam)
            favoriteTeamsAdapter.notifyDataSetChanged()
        }
    }

}