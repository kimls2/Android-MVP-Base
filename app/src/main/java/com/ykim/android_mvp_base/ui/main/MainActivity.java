package com.ykim.android_mvp_base.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.ykim.android_mvp_base.R;
import com.ykim.android_mvp_base.data.model.GalleryImage;
import com.ykim.android_mvp_base.ui.base.BaseActivity;
import com.ykim.android_mvp_base.ui.main.list.MainAdapter;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainMvp.View {

  @Inject MainPresenter presenter;

  @BindView(R.id.mainRv) RecyclerView mainRv;

  private MainAdapter mainAdapter;

  @Override protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    presenter.attachView(this);
    mainAdapter = new MainAdapter();
    mainRv.setLayoutManager(new LinearLayoutManager(this));
    mainRv.setAdapter(mainAdapter);
  }

  @Override public void showLoading(boolean show) {

  }

  @Override public void showError(String message) {

  }

  @Override public void showImages(List<GalleryImage> images) {
    mainAdapter.setData(images);
  }
}
