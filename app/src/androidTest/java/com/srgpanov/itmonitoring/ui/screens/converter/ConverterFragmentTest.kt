package com.srgpanov.itmonitoring.ui.screens.converter

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.srgpanov.itmonitoring.R
import com.srgpanov.itmonitoring.data.Repository
import com.srgpanov.itmonitoring.data.model.Valute
import com.srgpanov.itmonitoring.ui.MainActivity
import com.srgpanov.itmonitoring.ui.screens.currency_list.CurrencyListFragment
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import java.util.*


@RunWith(AndroidJUnit4::class)
class ConverterFragmentTest{





    @get:Rule
    public var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )


    @Test
    fun receiveInputValute() {
        val valute = Valute(
            id = "",
            charCode = "TMT",
            value = "",
            nominal = 0,
            numCode = 0,
            name = "",
            date = ""
        )
        val scenario = FragmentScenario.launchInContainer(ConverterFragment::class.java)

        scenario.onFragment { fragment ->
            val data = bundleOf(CurrencyListFragment.KEY_VALUTE to valute)
            fragment.parentFragmentManager.setFragmentResult(CurrencyListFragment.KEY_INPUT, data)
        }

        onView(withId(R.id.tv_input_code)).check(matches(withText("TMT")))
        onView(withId(R.id.btn_select_input_currency)).check(matches(withDrawable(R.drawable.ic_tmt)))
    }

    @Test
    fun receiveOutputValute() {
        val valute = Valute(
            id = "",
            charCode = "HUF",
            value = "",
            nominal = 0,
            numCode = 0,
            name = "",
            date = ""
        )
        val scenario = FragmentScenario.launchInContainer(ConverterFragment::class.java)

        scenario.onFragment { fragment ->
            val data = bundleOf(CurrencyListFragment.KEY_VALUTE to valute)
            fragment.parentFragmentManager.setFragmentResult(CurrencyListFragment.KEY_OUTPUT, data)
        }
        onView(withId(R.id.tv_output_code)).check(matches(withText("HUF")))
        onView(withId(R.id.btn_select_output_currency)).check(matches(withDrawable(R.drawable.ic_huf)))
    }


    fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("ImageView with drawable same as drawable with id $id")
        }

        override fun matchesSafely(view: View): Boolean {
            val context = view.context
            val expectedBitmap = context.getDrawable(id)?.toBitmap()

            return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
        }
    }






}