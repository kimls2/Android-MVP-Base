package com.ykim.android_mvp_base.ui.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ykim on 2017. 7. 12..
 */

public class BasePresenter<T extends BaseMvp.View> implements BaseMvp.Presenter<T> {

  protected CompositeDisposable disposable;
  private T mvpView;

  @Override public void attachView(T mvpView) {
    this.mvpView = mvpView;
    disposable = new CompositeDisposable();
  }

  @Override public void detachView() {
    this.mvpView = null;
    disposable.clear();
  }

  public boolean isViewAttached() {
    return mvpView != null;
  }

  public T getMvpView() {
    return mvpView;
  }
}
