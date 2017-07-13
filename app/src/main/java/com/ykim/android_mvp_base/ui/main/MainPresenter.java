package com.ykim.android_mvp_base.ui.main;

import com.ykim.android_mvp_base.data.DataManager;
import com.ykim.android_mvp_base.data.model.GalleryImage;
import com.ykim.android_mvp_base.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by ykim on 2017. 7. 12..
 */

public class MainPresenter extends BasePresenter<MainMvp.View> implements MainMvp.Presenter {

  private final DataManager dataManager;

  @Inject MainPresenter(DataManager dataManager) {
    this.dataManager = dataManager;
  }

  @Override public void attachView(MainMvp.View mvpView) {
    super.attachView(mvpView);
  }

  @Override public void loadImages(int page) {
    getMvpView().showLoading(true);
    disposable.add(dataManager.getGallery(page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<List<GalleryImage>>() {
          @Override public void onNext(@NonNull List<GalleryImage> images) {
            getMvpView().showImages(images);
          }

          @Override public void onError(@NonNull Throwable e) {
            getMvpView().showLoading(false);
            getMvpView().showError(e.getMessage());
          }

          @Override public void onComplete() {
            getMvpView().showLoading(false);
          }
        })

    );
  }
}
