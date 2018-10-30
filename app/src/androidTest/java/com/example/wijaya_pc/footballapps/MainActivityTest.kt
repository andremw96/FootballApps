package com.example.wijaya_pc.footballapps

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.wijaya_pc.footballapps.R.id.*
import com.example.wijaya_pc.footballapps.feature.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewLastMatchBehaviour() {
       Thread.sleep(1000)

       onView(withId(listMatch)).check(matches(isDisplayed()))
        Thread.sleep(1000)
       onView(withId(listMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(14))
        Thread.sleep(1000)
       onView(withId(listMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(1000)
       onView(withId(listMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
    }

    @Test
    fun testRecyclerViewNextMatchBehaviour() {
        Thread.sleep(1000)

        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(tab_next_match)).perform(click())
        Thread.sleep(1000)

        onView(withId(listMatch)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(listMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        Thread.sleep(1000)
        onView(withId(listMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        Thread.sleep(1000)
        onView(withId(listMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
    }

    @Test
    fun testRecyclerViewFavoritesBehaviour() {
        Thread.sleep(1000)

        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
        Thread.sleep(1000)

        onView(withId(listFav)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(listFav)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(14))
        Thread.sleep(1000)
        onView(withId(listFav)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Thread.sleep(1000)
        onView(withId(listFav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }


    @Test
    fun testAddFavoriteFromLastMatchBehaviour() {
        Thread.sleep(1000)

        onView(withId(listMatch)).check(matches(isDisplayed()))
        onView(ViewMatchers.withText("Crystal Palace")).perform(click())
        Thread.sleep(1000)

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(ViewMatchers.withText("Added to FavoriteMatches")).check(matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(1000)
        onView(ViewMatchers.withText("Removed from FavoriteMatches")).check(matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(1000)
        onView(ViewMatchers.withText("Added to FavoriteMatches")).check(matches(isDisplayed()))
        Thread.sleep(1000)

        pressBack()

        onView(withId(listMatch)).check(matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withId(listMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        Thread.sleep(1000)
        onView(withId(listMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        Thread.sleep(1000)

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(ViewMatchers.withText("Added to FavoriteMatches")).check(matches(isDisplayed()))

        pressBack()

        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun testAddFavoriteFromNextMatchBehaviour() {
        Thread.sleep(1000)

        onView(withId(listMatch)).check(matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(tab_next_match)).perform(click())
        Thread.sleep(1000)

        onView(withId(listMatch)).check(matches(isDisplayed()))
        onView(ViewMatchers.withText("Liverpool")).perform(click())
        Thread.sleep(1000)

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(ViewMatchers.withText("Added to FavoriteMatches")).check(matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(1000)
        onView(ViewMatchers.withText("Removed from FavoriteMatches")).check(matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(1000)
        onView(ViewMatchers.withText("Added to FavoriteMatches")).check(matches(isDisplayed()))
        Thread.sleep(1000)

        pressBack()

        onView(withId(listMatch)).check(matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withId(listMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
        Thread.sleep(1000)
        onView(withId(listMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()))
        Thread.sleep(1000)

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(ViewMatchers.withText("Added to FavoriteMatches")).check(matches(isDisplayed()))

        pressBack()

        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun testDeleteFavoriteFromFavoritesFragmentBehaviour() {
        Thread.sleep(1000)

        onView(withId(listMatch)).check(matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
        Thread.sleep(1000)

        onView(withId(listFav)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(listFav)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Thread.sleep(1000)
        onView(withId(listFav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(ViewMatchers.withText("Removed from FavoriteMatches")).check(matches(isDisplayed()))
        Thread.sleep(1000)
    }
}