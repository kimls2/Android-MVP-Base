package com.ykim.android_mvp_base.ui.main;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.ykim.android_mvp_base.R;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by ykim on 2017. 7. 13..
 */
@LargeTest @RunWith(AndroidJUnit4.class) public class MainActivityTest {

  @Rule public ActivityTestRule<MainActivity> mAddTaskIntentsTestRule =
      new ActivityTestRule<>(MainActivity.class);

  @Before public void setUp() throws Exception {
    //Espresso.registerIdlingResources(
    //    mAddTaskIntentsTestRule.getActivity().getCountingIdlingResource());
  }

  @After public void tearDown() throws Exception {

  }

  @Test public void basicScreenTest() {
    onView(withId(R.id.mainRv)).check(matches(isDisplayed()));
    onView(withId(R.id.mainRv)).perform(RecyclerViewActions.scrollToPosition(10));
    onView(withId(R.id.mainRv)).perform(RecyclerViewActions.scrollToPosition(0));
    onView(withId(R.id.mainRv)).perform(RecyclerViewActions.scrollToPosition(5));
  }

  //@SuppressLint("RtlHardcoded") @Test public void mainScreenTest() {
  //  onView(withId(R.id.webView)).check(matches(isDisplayed()));
  //  onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
  //  onView(withId(R.id.drawer_layout)).check(matches(isClosed(Gravity.LEFT)))
  //      .perform(DrawerActions.open());
  //
  //  onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
  //  //onView(withId(R.id.login_frame)).check(matches(isDisplayed()));
  //  for (int i = 0; i < 100; i++) {
  //    onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition(i));
  //  }
  //  onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition(0));
  //  onView(withId(R.id.drawer_layout)).check(matches(isOpen())).perform(DrawerActions.close());
  //}
}