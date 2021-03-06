package com.ykim.android_mvp_base.ui.main.list;

import android.content.Context;
import com.ykim.android_mvp_base.data.model.GalleryImage;
import com.ykim.android_mvp_base.ui.base.BaseListAdapter;
import com.ykim.android_mvp_base.ui.base.BaseViewHolder;
import com.ykim.android_mvp_base.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykim on 2017. 7. 12..
 */

public class MainAdapter extends BaseListAdapter<GalleryImage> {

  public void setData(List<GalleryImage> galleryImages) {
    items = new ArrayList<>();
    items.addAll(galleryImages);
    notifyDataSetChanged();
  }

  @Override protected BaseViewHolder<GalleryImage> getListItemView(Context context) {
    return new MainViewHolder(context);
  }

  @Override protected boolean areItemsTheSame(GalleryImage oldItem, GalleryImage newItem) {
    return Objects.equals(oldItem.getId(), newItem.getId());
  }

  @Override protected boolean areContentsTheSame(GalleryImage oldItem, GalleryImage newItem) {
    return Objects.equals(oldItem.getThumbnailSize(), newItem.getThumbnailSize());
  }
}
