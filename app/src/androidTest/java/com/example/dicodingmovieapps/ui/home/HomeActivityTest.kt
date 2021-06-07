package com.example.dicodingmovieapps.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.dicodingmovieapps.R
import com.example.dicodingmovieapps.utils.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadListMoviesAndTvSeries() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_error_message)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun loadDetailMoviesAndTvSeries() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )

        onView(withId(R.id.img_header_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.ic_score)).check(matches(isDisplayed()))
        onView(withId(R.id.ic_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.collapse_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_error_message)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))

        pressBack()
    }

    @Test
    fun addFavoritesAndViewFavorites() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )

        onView(withId(R.id.img_header_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.ic_score)).check(matches(isDisplayed()))
        onView(withId(R.id.ic_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.collapse_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))

        onView(withId(R.id.add_favorite)).perform(click())

        onView(withId(R.id.tv_error_message)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))

        pressBack()
    }
}