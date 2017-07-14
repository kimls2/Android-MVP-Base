package com.ykim.android_mvp_base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.ykim.android_mvp_base.di.AppInjector;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by ykim on 2017. 7. 12..
 */

public class MyApp extends Application implements HasActivityInjector {

  @Inject DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  private RefWatcher refWatcher;

  public static MyApp get(Context context) {
    return (MyApp) context.getApplicationContext();
  }

  public static RefWatcher getRefWatcher(Context context) {
    MyApp application = (MyApp) context.getApplicationContext();
    return application.refWatcher;
  }

  @Override public void onCreate() {
    super.onCreate();
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
      if (LeakCanary.isInAnalyzerProcess(this)) {
        return;
      }
      refWatcher = LeakCanary.install(this);
    }
    AppInjector.init(this);
  }

  @Override public AndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }
}
