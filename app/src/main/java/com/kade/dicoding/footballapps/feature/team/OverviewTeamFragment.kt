package com.kade.dicoding.footballapps.feature.team

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.kade.dicoding.footballapps.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

class OverviewTeamFragment : Fragment(), AnkoComponent<Context> {

    companion object {
        private const val ARG_TEAM_DESC = "team_desc"

        fun newInstance(teamDescription: String?): OverviewTeamFragment {
            val fragment = OverviewTeamFragment()
            val args = Bundle()
            args.putString(ARG_TEAM_DESC, teamDescription)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = matchParent) {
                topMargin = dip(5)
                bottomMargin = dip(10)
            }
            orientation = LinearLayout.VERTICAL
            topPadding = dip(8)
            leftPadding = dip(8)
            rightPadding = dip(8)

            scrollView {
                isVerticalScrollBarEnabled = false

                //team descrption
                textView {
                    id = R.id.team_descDetailTeam
                    text = arguments?.getString(OverviewTeamFragment.ARG_TEAM_DESC)
                }
            }
        }
    }

}