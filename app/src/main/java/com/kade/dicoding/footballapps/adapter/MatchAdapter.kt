package com.kade.dicoding.footballapps.adapter

import android.content.Intent
import android.os.Build
import android.provider.CalendarContract
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.kade.dicoding.footballapps.*
import com.kade.dicoding.footballapps.R.id.*
import com.kade.dicoding.footballapps.model.Match
import com.kade.dicoding.footballapps.ui.MatchUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import java.util.*

class MatchAdapter(
    private val matches: List<Match>,
    private val listener: (Match) -> Unit
) : RecyclerView.Adapter<MatchViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }
}

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val matchDate: TextView = view.find(match_date)
    private val matchTime: TextView = view.find(match_time)
    private val matchHomeTeam: TextView = view.find(match_home_team)
    private val matchHomeScore: TextView = view.find(match_home_score)
    private val matchAwayScore: TextView = view.find(match_away_score)
    private val matchAwayTeam: TextView = view.find(match_away_team)

    private val btnToCalendar: ImageButton = view.find(btn_to_calendar)

    fun bindItem(matches: Match, listener: (Match) -> Unit) {
        val matchTime = if (matches.matchTime == null) "00:00:00" else matches.matchTime

        val matchDateTime = dateTimeToSimpleString(toGMTFormat(dateToSimpleString(matches.matchDate), matchTime))

        matchDate.text = matchDateTime.substring(0, 16)
        this.matchTime.text = matchDateTime.substring(17, 22)

        matchHomeTeam.text = matches.homeTeam
        matchHomeScore.text = matches.homeScore

        matchAwayScore.text = matches.awayScore
        matchAwayTeam.text = matches.awayTeam

        if (!(matches.homeScore.isNullOrBlank()) && !(matches.awayScore.isNullOrBlank())) {
            btnToCalendar.invisible()
        }

        btnToCalendar.setOnClickListener {
            val calendarDate = dateTimeToSimpleStringforCalendar(
                toGMTFormatforCalendar(
                    dateToSimpleStringforCalendar(matches.matchDate),
                    matchTime
                )
            )
            val year = calendarDate.substring(0, 4).toInt()
            val month = calendarDate.substring(5, 7).toInt()
            val day = calendarDate.substring(8, 10).toInt()
            val hour = calendarDate.substring(11, 13).toInt()
            val minute = calendarDate.substring(14, 16).toInt()

            val startMatch: Long = Calendar.getInstance().run {
                set(year, month - 1, day, hour, minute)
                timeInMillis
            }

            val endMatch: Long = Calendar.getInstance().run {
                set(year, month - 1, day, hour + 2, minute)
                timeInMillis
            }

            val calendarIntent = Intent(Intent.ACTION_INSERT)
            calendarIntent.type = "vnd.android.cursor.item/event"
            calendarIntent.putExtra(CalendarContract.Events.TITLE, "${matches.homeTeam} vs ${matches.awayTeam}")
            calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMatch)
            calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMatch)
            itemView.context.startActivity(calendarIntent)
        }

        itemView.setOnClickListener {
            listener(matches)
        }
    }
}