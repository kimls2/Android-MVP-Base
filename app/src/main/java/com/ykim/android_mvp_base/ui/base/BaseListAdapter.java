//package com.ykim.android_mvp_base.ui.base;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//import java.util.List;
//
///**
// * Created by ykim on 2017. 7. 12..
// */
//
//@SuppressWarnings("ALL") public abstract class BaseListAdapter<T>
//    extends RecyclerView.Adapter<BaseListAdapter.ViewHolder> {
//
//  protected List<T> items;
//
//  abstract BaseViewHolder<T> getListItemView(Context context);
//
//  @Override public int getItemCount() {
//    return items == null ? 0 : items.size();
//  }
//
//  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//    return new ViewHolder(getListItemView(parent.getContext()));
//  }
//
//  @Override public void onBindViewHolder(ViewHolder holder, int position) {
//    //holder.baseViewHolder.bind(items.get(position));
//
//  }
//
//  class ViewHolder extends RecyclerView.ViewHolder {
//
//    BaseViewHolder<T> baseViewHolder;
//
//    ViewHolder(View itemView) {
//      super(itemView);
//      baseViewHolder = (BaseViewHolder<T>) itemView;
//    }
//  }
//}
