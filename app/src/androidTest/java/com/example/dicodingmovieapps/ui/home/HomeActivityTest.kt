package com.example.dicodingmovieapps.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.dicodingmovieapps.R
import com.example.dicodingmovieapps.utils.DummyData
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovies = DummyData.generateDataMovies()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadListMoviesAndTvSeries() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_error_message)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun loadDetailMoviesAndTvSeries() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.img_header_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.img_cast1)).check(matches(isDisplayed()))
        onView(withId(R.id.img_cast2)).check(matches(isDisplayed()))
        onView(withId(R.id.img_cast3)).check(matches(isDisplayed()))
        onView(withId(R.id.ic_score)).check(matches(isDisplayed()))
        onView(withId(R.id.ic_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.collapse_toolbar)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_release_date)).check(matches(withText(dummyMovies[0].releaseDate)))
        onView(withId(R.id.tv_duration)).check(matches(withText(dummyMovies[0].duration)))
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyMovies[0].genre)))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovies[0].overview)))

        onView(withId(R.id.tv_error_message)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        pressBack()
    }
}