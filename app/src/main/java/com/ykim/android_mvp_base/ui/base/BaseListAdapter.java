package com.ykim.android_mvp_base.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by ykim on 2017. 7. 12..
 */

@SuppressWarnings("ALL") public abstract class BaseListAdapter<T>
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  protected List<T> items;

  protected abstract BaseViewHolder<T> getListItemView(Context context);

  @Override public int getItemCount() {
    return items == null ? 0 : items.size();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new TestViewHolder(getListItemView(parent.getContext()));
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((TestViewHolder) holder).view.bind(items.get(position));
  }

  class TestViewHolder extends RecyclerView.ViewHolder {

    BaseViewHolder<T> view;

    TestViewHolder(BaseViewHolder<T> itemView) {
      super(itemView);
      view = itemView;
    }
  }
}
