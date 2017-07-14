package com.ykim.android_mvp_base;

import com.ykim.android_mvp_base.data.DataManager;
import com.ykim.android_mvp_base.injection.AppTestComponent;
import com.ykim.android_mvp_base.injection.AppTestModule;
import com.ykim.android_mvp_base.injection.DaggerAppTestComponent;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by ykim on 2017. 7. 14..
 */

public class TestComponentRule implements TestRule {

  private AppTestComponent testComponent;

  public DataManager getMockDataManager() {
    return testComponent.dataManager();
  }

  @Override public Statement apply(Statement base, Description description) {
    return new Statement() {
      @Override public void evaluate() throws Throwable {
        try {
          setupDaggerTestComponentInApplication();
          RxAndroidPlugins.reset();
          RxJavaPlugins.reset();
          RxAndroidPlugins.setInitMainThreadSchedulerHandler(
              schedulerCallable -> Schedulers.trampoline());
          RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
          base.evaluate();
        } finally {
          testComponent = null;
          RxAndroidPlugins.reset();
          RxJavaPlugins.reset();
        }
      }
    };
  }

  private void setupDaggerTestComponentInApplication() {
    testComponent = DaggerAppTestComponent.builder().appTestModule(new AppTestModule()).build();
  }
}
