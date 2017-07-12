package com.ykim.android_mvp_base.ui.main;

import com.ykim.android_mvp_base.data.DataManager;
import com.ykim.android_mvp_base.ui.base.BasePresenter;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by ykim on 2017. 7. 12..
 */

public class MainPresenter extends BasePresenter<MainMvp.View> {

  private final DataManager dataManager;

  @Inject public MainPresenter(DataManager dataManager) {
    this.dataManager = dataManager;
  }

  public void test() {
    dataManager.getGallery("test", "test", "test", 1, false);
    Timber.e("test");
  }
}
