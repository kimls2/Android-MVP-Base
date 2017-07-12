package com.ykim.android_mvp_base.ui.base;

/**
 * Created by ykim on 2017. 7. 12..
 */

public interface BaseMvp {

  interface View {

    void showLoading(boolean show);

    void showError(String message);
  }

  interface Presenter<T> {

    void attachView(T mvpView);

    void detachView();
  }
}
