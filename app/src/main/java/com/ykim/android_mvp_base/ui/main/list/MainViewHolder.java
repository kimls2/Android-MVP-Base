package com.ykim.android_mvp_base.ui.main.list;

import android.content.Context;
import android.widget.ImageView;
import butterknife.BindView;
import com.ykim.android_mvp_base.R;
import com.ykim.android_mvp_base.data.model.GalleryImage;
import com.ykim.android_mvp_base.ui.base.BaseViewHolder;
import com.ykim.android_mvp_base.util.GlideApp;

/**
 * Created by ykim on 2017. 7. 12..
 */

public class MainViewHolder extends BaseViewHolder<GalleryImage> {

  @BindView(R.id.thumbnailIv) ImageView thumbnailIv;

  public MainViewHolder(Context context) {
    super(context);
  }

  @Override protected int layoutResId() {
    return R.layout.item_main;
  }

  @Override public void bind(GalleryImage item) {
    GlideApp.with(getContext()).load(item.getThumbnailSize()).fitCenter().into(thumbnailIv);
  }
}
