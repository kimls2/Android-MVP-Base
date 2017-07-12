package com.ykim.android_mvp_base.ui.main;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import com.ykim.android_mvp_base.R;
import com.ykim.android_mvp_base.ui.base.BaseActivity;
import javax.inject.Inject;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

  @Inject MainPresenter presenter;

  @BindView(R.id.test) TextView test;

  @Override protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Timber.e("1234567");
    test.setText("abcd");
    presenter.test();
  }
}
