package com.ykim.android_mvp_base.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by ykim on 2017. 7. 12..
 */

@SuppressWarnings("ALL") public abstract class BaseListAdapter<T>
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private int dataVersion = 0;

  protected List<T> items;

  protected abstract BaseViewHolder<T> getListItemView(Context context);

  protected abstract boolean areItemsTheSame(T oldItem, T newItem);

  protected abstract boolean areContentsTheSame(T oldItem, T newItem);

  @SuppressLint("StaticFieldLeak") @MainThread public void replace(List<T> update) {
    dataVersion++;
    if (items == null) {
      if (update == null) {
        return;
      }
      items = update;
      notifyDataSetChanged();
    } else if (update == null) {
      int oldSize = items.size();
      items = null;
      notifyItemRangeRemoved(0, oldSize);
    } else {
      final int startVersion = dataVersion;
      final List<T> oldItems = items;
      new AsyncTask<Void, Void, DiffUtil.DiffResult>() {
        @Override protected DiffUtil.DiffResult doInBackground(Void... voids) {
          return DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override public int getOldListSize() {
              return oldItems.size();
            }

            @Override public int getNewListSize() {
              return update.size();
            }

            @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
              T oldItem = oldItems.get(oldItemPosition);
              T newItem = update.get(newItemPosition);
              return BaseListAdapter.this.areItemsTheSame(oldItem, newItem);
            }

            @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
              T oldItem = oldItems.get(oldItemPosition);
              T newItem = update.get(newItemPosition);
              return BaseListAdapter.this.areContentsTheSame(oldItem, newItem);
            }
          });
        }

        @Override protected void onPostExecute(DiffUtil.DiffResult diffResult) {
          if (startVersion != dataVersion) {
            // ignore update
            return;
          }
          items = update;
          diffResult.dispatchUpdatesTo(BaseListAdapter.this);
        }
      }.execute();
    }
  }

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
