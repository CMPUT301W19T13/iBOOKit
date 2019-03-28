package com.example.ibookit.View;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.ibookit.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class AddBookTest {

    private String title;
    private String author;
    private String isbn;


    @Rule
    public ActivityTestRule<AddBookOwnerActivity> activityRule
            = new ActivityTestRule<>(AddBookOwnerActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        title = "this is title";
        author = "this is author";
        isbn = "2345676543";
    }


    @Test
    public void addText_sameActivity(){
        onView(withId(R.id.bookTitleAdd))
                .perform(typeText(title));
        onView(withId(R.id.bookAuthorAdd))
                .perform(typeText(author));
        onView(withId(R.id.bookISBNAdd))
                .perform(typeText(isbn));


        onView(withText(title)).check(matches(isDisplayed()));
        onView(withText(author)).check(matches(isDisplayed()));
        onView(withText(isbn)).check(matches(isDisplayed()));

    }
}
