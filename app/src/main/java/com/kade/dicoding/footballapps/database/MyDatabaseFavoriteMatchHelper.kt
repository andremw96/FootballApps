package com.kade.dicoding.footballapps.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kade.dicoding.footballapps.model.FavoriteMatches
import org.jetbrains.anko.db.*

class MyDatabaseFavoriteMatchHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: MyDatabaseFavoriteMatchHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseFavoriteMatchHelper {
            if (instance == null) {
                instance = MyDatabaseFavoriteMatchHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseFavoriteMatchHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            FavoriteMatches.TABLE_FAVORITE_MATCH, true,
            FavoriteMatches.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatches.MATCH_ID to TEXT + UNIQUE,
            FavoriteMatches.MATCH_DATE to TEXT,
            FavoriteMatches.MATCH_TIME to TEXT,
            FavoriteMatches.HOME_TEAM_ID to TEXT,
            FavoriteMatches.AWAY_TEAM_ID to TEXT,
            FavoriteMatches.HOME_TEAM_NAME to TEXT,
            FavoriteMatches.AWAY_TEAM_NAME to TEXT,
            FavoriteMatches.HOME_TEAM_SCORE to TEXT,
            FavoriteMatches.AWAY_TEAM_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteMatches.TABLE_FAVORITE_MATCH, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseFavoriteMatchHelper
    get() = MyDatabaseFavoriteMatchHelper.getInstance(applicationContext)