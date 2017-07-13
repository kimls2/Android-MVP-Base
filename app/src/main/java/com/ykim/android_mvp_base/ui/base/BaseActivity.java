package com.ykim.android_mvp_base.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import com.squareup.leakcanary.RefWatcher;
import com.ykim.android_mvp_base.BuildConfig;
import com.ykim.android_mvp_base.MyApp;
import com.ykim.android_mvp_base.di.Injectable;

/**
 * Created by ykim on 2017. 7. 12..
 */

public abstract class BaseActivity extends AppCompatActivity implements Injectable {

  protected abstract @LayoutRes int getLayoutResId();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());
    ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (BuildConfig.DEBUG) {
      RefWatcher refWatcher = MyApp.getRefWatcher(this);
      refWatcher.watch(this);
    }
  }
}
