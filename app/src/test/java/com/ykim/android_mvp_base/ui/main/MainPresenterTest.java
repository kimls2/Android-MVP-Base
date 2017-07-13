package com.ykim.android_mvp_base.ui.main;

import com.ykim.android_mvp_base.data.DataManager;
import com.ykim.android_mvp_base.data.model.GalleryImage;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by ykim on 2017. 7. 13..
 */
@RunWith(MockitoJUnitRunner.class) public class MainPresenterTest {

  @Mock MainMvp.View mockMvpView;
  @Mock DataManager mockDataManager;
  @InjectMocks MainPresenter mainPresenter;

  @Before public void setUp() throws Exception {
    RxAndroidPlugins.reset();
    RxJavaPlugins.reset();
    RxAndroidPlugins.setInitMainThreadSchedulerHandler(
        schedulerCallable -> Schedulers.trampoline());
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    mainPresenter.attachView(mockMvpView);
  }

  @After public void tearDown() throws Exception {
    mainPresenter.detachView();
    RxAndroidPlugins.reset();
    RxJavaPlugins.reset();
  }

  @Test public void loadImageSuccessful() {
    List<GalleryImage> galleryImageList = new ArrayList<>();
    galleryImageList.add(new GalleryImage().setId("1"));
    galleryImageList.add(new GalleryImage().setId("2"));
    Mockito.when(mockDataManager.getGallery(0)).thenReturn(Observable.just(galleryImageList));
    mainPresenter.loadImages(0);
    verify(mockMvpView).showLoading(true);
    verify(mockMvpView).showImages(galleryImageList);
    verify(mockMvpView).showLoading(false);
  }

  @Test public void loadImagesFail() {
    Mockito.when(mockDataManager.getGallery(0))
        .thenReturn(Observable.error(new NullPointerException()));
    mainPresenter.loadImages(0);
    verify(mockMvpView).showLoading(true);
    verify(mockMvpView, never()).showImages(anyListOf(GalleryImage.class));
    verify(mockMvpView).showLoading(false);
    verify(mockMvpView).showError(new NullPointerException().getMessage());
  }
}