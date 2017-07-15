package com.ykim.android_mvp_base.ui.main;

import com.ykim.android_mvp_base.data.DataManager;
import com.ykim.android_mvp_base.data.model.GalleryImage;
import com.ykim.android_mvp_base.ui.base.BasePresenter;
import com.ykim.android_mvp_base.util.RxEventBus;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by ykim on 2017. 7. 12..
 */

public class MainPresenter extends BasePresenter<MainMvp.View> implements MainMvp.Presenter {

  private final DataManager dataManager;
  private final RxEventBus eventBus;

  @Inject MainPresenter(DataManager dataManager, RxEventBus eventBus) {
    this.dataManager = dataManager;
    this.eventBus = eventBus;
    setEventBus();
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
        }));
  }

  public void flatMapTest() {
    this.galleryImages.clear();
    getMvpView().showLoading(true);
    disposable.add(dataManager.getGallery(0)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap(new Function<List<GalleryImage>, ObservableSource<GalleryImage>>() {
          @Override public ObservableSource<GalleryImage> apply(@NonNull List<GalleryImage> images)
              throws Exception {
            return Observable.fromIterable(images);
          }
        })
        .subscribeWith(new DisposableObserver<GalleryImage>() {
          @Override public void onNext(@NonNull GalleryImage image) {
            addImage(image);
          }

          @Override public void onError(@NonNull Throwable throwable) {
            getMvpView().showLoading(false);
            getMvpView().showError(throwable.getMessage());
          }

          @Override public void onComplete() {
            getMvpView().showLoading(false);
            getMvpView().showImages(galleryImages);
          }
        }));
  }

  private List<GalleryImage> galleryImages = new ArrayList<>();

  private void addImage(GalleryImage image) {
    galleryImages.add(image);
  }

  private void setEventBus() {
    disposable.add(eventBus.getEvents().subscribe(event -> {
        }, throwable -> getMvpView().showError(throwable.getMessage())

    ));
  }
}
