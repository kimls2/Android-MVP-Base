package com.ykim.android_mvp_base.ui.main;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.ykim.android_mvp_base.R;
import com.ykim.android_mvp_base.TestComponentRule;
import com.ykim.android_mvp_base.data.model.GalleryImage;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.when;

/**
 * Created by ykim on 2017. 7. 13..
 */
@LargeTest @RunWith(AndroidJUnit4.class) public class MainScreenTest {

  public final TestComponentRule component = new TestComponentRule();

  public final ActivityTestRule<MainActivity> mainScreeTestRule =
      new ActivityTestRule<>(MainActivity.class, true, true);

  @Rule public TestRule chain = RuleChain.outerRule(component).around(mainScreeTestRule);

  private List<GalleryImage> galleryImages = new ArrayList<>();

  @Before public void setUp() throws Exception {
    galleryImages.add(new GalleryImage().setId("0"));
    galleryImages.add(new GalleryImage().setId("1"));
    galleryImages.add(new GalleryImage().setId("2"));
  }

  @After public void tearDown() throws Exception {

  }

  @Test public void basicScreenTest() {
    when(component.getMockDataManager().getGallery(0)).thenReturn(Observable.just(galleryImages));
    onView(withId(R.id.mainRv)).check(matches(isDisplayed()));
    onView(withId(R.id.mainRv)).perform(RecyclerViewActions.scrollToPosition(10));
    onView(withId(R.id.mainRv)).perform(RecyclerViewActions.scrollToPosition(0));
    onView(withId(R.id.mainRv)).perform(RecyclerViewActions.scrollToPosition(5));
  }

  @Test public void whenLoadingImageFails_shouldShowSnackBarErrorMessage() {
    when(component.getMockDataManager().getGallery(0)).thenReturn(
        Observable.error(new NullPointerException()));
  }
}