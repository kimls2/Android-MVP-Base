package com.ykim.android_mvp_base.util;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * This rule registers SchedulerHooks for RxJava and RxAndroid to ensure that subscriptions
 * always subscribeOn and observeOn Schedulers.immediate().
 * Warning, this rule will reset RxAndroidPlugins and RxJavaPlugins before and after each test so
 * if the application code uses RxJava plugins this may affect the behaviour of the testing method.
 */
public class RxSchedulersOverrideRule implements TestRule {

  @Override public Statement apply(final Statement base, Description description) {
    return new Statement() {
      @Override public void evaluate() throws Throwable {
        RxAndroidPlugins.reset();
        RxJavaPlugins.reset();
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
            schedulerCallable -> Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        base.evaluate();
        RxAndroidPlugins.reset();
        RxJavaPlugins.reset();
      }
    };
  }
}
