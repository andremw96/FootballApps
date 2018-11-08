package com.kade.dicoding.footballapps.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kade.dicoding.footballapps.model.FavoriteTeams
import org.jetbrains.anko.db.*

class MyDatabaseFavoriteTeamsHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseFavoriteTeamsHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseFavoriteTeamsHelper {
            if (instance == null) {
                instance = MyDatabaseFavoriteTeamsHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseFavoriteTeamsHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        //here you create tables
        db.createTable(
            FavoriteTeams.TABLE_FAVORITE_TEAMS, true,
            FavoriteTeams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeams.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeams.TEAM_NAME to TEXT,
            FavoriteTeams.TEAM_BADGE to TEXT,
            FavoriteTeams.TEAM_FORMED_YEAR to TEXT,
            FavoriteTeams.TEAM_STADIUM to TEXT,
            FavoriteTeams.TEAM_DESCRIPTION to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //here you can upgrade tables
        db.dropTable(FavoriteTeams.TABLE_FAVORITE_TEAMS, true)
    }
}

// access property for context
val Context.databaseTeam: MyDatabaseFavoriteTeamsHelper
    get() = MyDatabaseFavoriteTeamsHelper.getInstance(applicationContext)