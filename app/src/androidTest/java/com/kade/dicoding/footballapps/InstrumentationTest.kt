package com.kade.dicoding.footballapps

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.ImageButton
import com.kade.dicoding.footballapps.R.id.*
import com.kade.dicoding.footballapps.feature.main.MainActivity
import com.kade.dicoding.footballapps.idling.IdlingResource
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    var idlingResource: IdlingResource? = null

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        idlingResource = IdlingResource(2000)
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }


    @Test
    fun testRecyclerViewLastMatch() {
        onView(allOf(withTagValue(`is`("Last_Match" as Any)), withId(listMatch))).check(matches(isDisplayed()))

        onView(
            allOf(
                withTagValue(`is`("Last_Match" as Any)),
                withId(listMatch)
            )
        ).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(14))

        onView(
            allOf(
                withTagValue(`is`("Last_Match" as Any)),
                withId(listMatch)
            )
        ).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(
            allOf(
                withTagValue(`is`("Last_Match" as Any)),
                withId(listMatch)
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
    }

    @Test
    fun testRecyclerViewNextMatch() {
        onView(withId(tabs)).check(matches(isDisplayed()))

        onView(withContentDescription("Next Match")).check(matches(isDisplayed()))
        onView(withContentDescription("Next Match")).perform(click())

        onView(allOf(withTagValue(`is`("Next_Match" as Any)), withId(listMatch))).check(matches(isDisplayed()))

        onView(
            allOf(
                withTagValue(`is`("Next_Match" as Any)),
                withId(listMatch)
            )
        ).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))

        onView(
            allOf(
                withTagValue(`is`("Next_Match" as Any)),
                withId(listMatch)
            )
        ).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))

        onView(
            allOf(
                withTagValue(`is`("Next_Match" as Any)),
                withId(listMatch)
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))
    }

    @Test
    fun testRecyclerViewFavMatch() {

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.favorites)).perform(click())

        onView(allOf(withTagValue(`is`("fav_match" as Any)), withId(listFav))).check(matches(isDisplayed()))

        onView(
            allOf(
                withTagValue(`is`("fav_match" as Any)),
                withId(listFav)
            )
        ).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(14))

        onView(
            allOf(
                withTagValue(`is`("fav_match" as Any)),
                withId(listFav)
            )
        ).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(
            allOf(
                withTagValue(`is`("fav_match" as Any)),
                withId(listFav)
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
    }

    @Test
    fun testRecyclerViewFavTeam() {

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.favorites)).perform(click())

        onView(withId(tabsFavorite)).check(matches(isDisplayed()))

        onView(withContentDescription("Team")).check(matches(isDisplayed()))
        onView(withContentDescription("Team")).perform(click())

        onView(allOf(withTagValue(`is`("fav_team" as Any)), withId(listFav))).check(matches(isDisplayed()))

        onView(
            allOf(
                withTagValue(`is`("fav_team" as Any)),
                withId(listFav)
            )
        ).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))

        onView(
            allOf(
                withTagValue(`is`("fav_team" as Any)),
                withId(listFav)
            )
        ).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        onView(
            allOf(
                withTagValue(`is`("fav_team" as Any)),
                withId(listFav)
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun testRecyclerViewTeam() {

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_teams)).perform(click())

        onView(withId(listTeam)).check(matches(isDisplayed()))

        onView(withId(listTeam)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))

        onView(withId(listTeam)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))

        onView(withId(listTeam)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )

        pressBack()

        onView(ViewMatchers.withId(listTeam)).check(matches(isDisplayed()))

        onView(withId(spinner_teams)).check(matches(isDisplayed()))
        onView(withId(spinner_teams)).perform(click())
        onView(withText("Spanish La Liga")).perform(click())

        onView(withId(listTeam)).check(matches(isDisplayed()))

        onView(withId(listTeam)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        onView(withId(listTeam)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView(withId(listTeam)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
    }

    @Test
    fun testFavoriteTeam() {

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_teams)).perform(click())

        onView(withId(listTeam)).check(matches(isDisplayed()))

        onView(withId(listTeam)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(ViewMatchers.withText("Added to FavoriteTeams")).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).perform(click())

        onView(ViewMatchers.withText("Removed from FavoriteTeams")).check(matches(isDisplayed()))
    }

    @Test
    fun testFavoriteLastMatch() {

        onView(allOf(withTagValue(`is`("Last_Match" as Any)), withId(listMatch))).check(matches(isDisplayed()))

        onView(
            allOf(
                withTagValue(`is`("Last_Match" as Any)),
                withId(listMatch)
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(ViewMatchers.withText("Added to FavoriteMatches")).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).perform(click())

        onView(ViewMatchers.withText("Removed from FavoriteMatches")).check(matches(isDisplayed()))

    }

    @Test
    fun testFavoriteNextMatch() {
        onView(withId(tabs)).check(matches(isDisplayed()))

        onView(withContentDescription("Next Match")).check(matches(isDisplayed()))
        onView(withContentDescription("Next Match")).perform(click())

        onView(allOf(withTagValue(`is`("Next_Match" as Any)), withId(listMatch))).check(matches(isDisplayed()))

        onView(
            allOf(
                withTagValue(`is`("Next_Match" as Any)),
                withId(listMatch)
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(ViewMatchers.withText("Added to FavoriteMatches")).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).perform(click())

        onView(ViewMatchers.withText("Removed from FavoriteMatches")).check(matches(isDisplayed()))

    }

    @Test
    fun testDetailViewTeam() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_teams)).perform(click())

        onView(withId(listTeam)).check(matches(isDisplayed()))

        onView(withId(listTeam)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(tabs_detail_team)).check(matches(isDisplayed()))
        onView(withContentDescription("Overview")).check(matches(isDisplayed()))

        onView(withContentDescription("Players")).check(matches(isDisplayed()))

        onView(withContentDescription("Players")).perform(click())

        onView(withId(listPlayer)).check(matches(isDisplayed()))

        onView(withId(listPlayer)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        onView(withId(listPlayer)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        onView(withId(listPlayer)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
    }

    @Test
    fun testAddMatchToCalendar() {
        onView(withId(tabs)).check(matches(isDisplayed()))

        onView(withContentDescription("Next Match")).check(matches(isDisplayed()))
        onView(withContentDescription("Next Match")).perform(click())

        onView(allOf(withTagValue(`is`("Next_Match" as Any)), withId(listMatch))).check(matches(isDisplayed()))

        onView(
            allOf(
                withTagValue(`is`("Next_Match" as Any)),
                withId(listMatch)
            )
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                MyImageButtonAction.clickChildViewWithId(R.id.btn_to_calendar)
            )
        )
    }

    @Test
    fun testSearchTeam() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_teams)).perform(click())

        onView(withId(button_search)).check(matches(isDisplayed()))
        onView(withId(button_search)).perform(click())

        onView(allOf(withId(action_search), withEffectiveVisibility(Visibility.VISIBLE))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.action_search), withEffectiveVisibility(Visibility.VISIBLE))).perform(
            typeSearchViewText("Chelsea")
        )

        onView(withId(rv_search_team)).check(matches(isDisplayed()))

        onView(withId(rv_search_team)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

    }

    @Test
    fun testSearchMatch() {
        onView(withId(button_search)).check(matches(isDisplayed()))
        onView(withId(button_search)).perform(click())

        onView(allOf(withId(action_search), withEffectiveVisibility(Visibility.VISIBLE))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.action_search), withEffectiveVisibility(Visibility.VISIBLE))).perform(
            typeSearchViewText("Chelsea")
        )

        onView(withId(rv_search)).check(matches(isDisplayed()))

        onView(withId(rv_search)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

    }

}

object MyImageButtonAction {

    fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<ImageButton>(id)
                v.performClick()
            }
        }
    }

}

fun typeSearchViewText(text: String): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            //Ensure that only apply if it is a SearchView and if it is visible.
            return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
        }

        override fun getDescription(): String {
            return "Change view text"
        }

        override fun perform(uiController: UiController, view: View) {
            (view as SearchView).setQuery(text, false)
        }
    }
}