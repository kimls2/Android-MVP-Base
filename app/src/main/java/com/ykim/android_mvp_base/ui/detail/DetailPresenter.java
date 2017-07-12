package com.ykim.android_mvp_base.ui.detail;

import com.ykim.android_mvp_base.data.DataManager;
import com.ykim.android_mvp_base.ui.base.BasePresenter;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by ykim on 2017. 7. 12..
 */

public class DetailPresenter extends BasePresenter<DetailMvp.View> {

  private final DataManager dataManager;

  @Inject DetailPresenter(DataManager dataManager) {
    this.dataManager = dataManager;
  }

  public void test() {
    //dataManager.getGallery("test", "test", "test", 1, false);
    Timber.e("test");
  }
}
