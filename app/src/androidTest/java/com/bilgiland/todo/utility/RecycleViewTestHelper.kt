package com.bilgiland.todo.utility

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher


object RecycleViewTestHelper {
    fun getCountFromRecyclerView(RecyclerViewId: Int): Int {
        val count = intArrayOf(0)
        val matcher =
            object : TypeSafeMatcher<View>() {
                override fun matchesSafely(item: View): Boolean {
                    count[0] = (item as RecyclerView).adapter!!.itemCount
                    return true
                }

                override fun describeTo(description: Description) {}
            }
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(RecyclerViewId),
                ViewMatchers.isDisplayed()
            )
        ).check(ViewAssertions.matches(matcher))
        val result = count[0]
        count[0] = 0
        return result
    }

}