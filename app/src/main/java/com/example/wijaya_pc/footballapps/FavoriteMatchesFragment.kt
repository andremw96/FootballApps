package com.example.wijaya_pc.footballapps

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wijaya_pc.footballapps.R.color.colorAccent
import com.example.wijaya_pc.footballapps.adapter.FavoriteMatchesAdapter
import com.example.wijaya_pc.footballapps.database.database
import com.example.wijaya_pc.footballapps.model.FavoriteMatches
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteMatchesFragment : Fragment(), AnkoComponent<Context> {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listFav: RecyclerView

    private var favorites: MutableList<FavoriteMatches> = mutableListOf()
    private lateinit var adapter: FavoriteMatchesAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteMatchesAdapter(favorites) {
            ctx.startActivity<DetailActivity>(
                "id" to "${it.matchId}",
                "homeTeamId" to "${it.homeTeamId}",
                "awayTeamId" to "${it.awayTeamId}"
            )
        }

        listFav.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
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
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }

    private fun showFavorite() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoriteMatches.TABLE_FAVORITE_MATCH)
            var favorite = result.parseList(classParser<FavoriteMatches>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

}