package com.ykim.android_mvp_base.ui.detail;

import android.os.Bundle;
import com.ykim.android_mvp_base.R;
import com.ykim.android_mvp_base.ui.base.BaseActivity;
import timber.log.Timber;

public class DetailActivity extends BaseActivity {

  @Override protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Timber.e("1234567");
  }
}
