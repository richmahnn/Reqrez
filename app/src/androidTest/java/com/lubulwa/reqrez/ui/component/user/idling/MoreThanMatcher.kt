package com.lubulwa.reqrez.ui.component.user.idling

import android.view.View;
import android.widget.TextView;
import androidx.test.espresso.matcher.BoundedMatcher

import org.hamcrest.Description;
import java.util.regex.Matcher

class MoreThanMatcher {

    fun moreThan(expectedValue: Int): BoundedMatcher<View, TextView> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun matchesSafely(textView: TextView): Boolean {
                val actualValue = java.lang.Float.valueOf(textView.text.toString())!!
                return actualValue.toInt() == expectedValue
            }

            override fun describeTo(description: Description) {
                description.appendText("more than: $expectedValue")
            }
        }
    }
}