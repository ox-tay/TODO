package com.bilgiland.todo

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.bilgiland.todo.ui.main.MainActivity
import com.bilgiland.todo.utility.RecycleViewTestHelper.getCountFromRecyclerView
import com.bilgiland.todo.utility.getRandomString
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * ui test of task add
 */
@RunWith(AndroidJUnit4::class)
class AddTodoTest {

    private lateinit var activity: Activity

    @get:Rule
    var mMainActivityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    // get activity
    @Before
    fun init() {
        activity = mMainActivityTestRule.activity
    }

    @Test
    fun addTodo() {
        onView(withId(R.id.fab_add)).perform(click())

        val todoName = getRandomString(10)

        onView(withId(R.id.edt_todo)).perform(click(), typeText(todoName))
        onView(withId(R.id.btn_add)).perform(click())

        val recSize = getCountFromRecyclerView(R.id.rec_main)
        onView(
            allOf(
                withId(R.id.rec_main),
                isDisplayed()
            )
        ).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(recSize - 1))
            .check(matches(atPosition(recSize - 1, hasDescendant(withText(todoName)))))
    }

    @Test
    fun addEmptyTodo() {
        onView(withId(R.id.fab_add)).perform(click())
        onView(withId(R.id.btn_add)).perform(click())
       // checkShowToast(activity.getString(R.string.enter_name))
    }

    @Test
    fun cancelAddTodo() {
        onView(withId(R.id.fab_add)).perform(click())
        onView(withId(R.id.btn_cancel)).perform(click())
        onView(withId(R.id.btn_cancel)).check(doesNotExist())
    }


    @Test
    fun deleteAll() {
        onView(withId(R.id.img_delete_all)).perform(click())
        Assert.assertEquals(0, getCountFromRecyclerView(R.id.rec_main))
    }

    private fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
        //checkNotNull(itemMatcher);
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder =
                    view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}
