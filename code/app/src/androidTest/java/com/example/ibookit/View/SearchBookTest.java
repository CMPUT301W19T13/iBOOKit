package com.example.ibookit.View;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.ibookit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class SearchBookTest {

    private String book;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Rule
    public ActivityTestRule<HomeSearchActivity> activityRule
            = new ActivityTestRule<>(HomeSearchActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.

        book = "harry potter";
    }



    @Test
    public void addText_sameActivity(){
        onView(withId(R.id.re_search_bar))
                .perform(typeText(book));

        onView(withText(book)).check(matches(isDisplayed()));


    }
}
