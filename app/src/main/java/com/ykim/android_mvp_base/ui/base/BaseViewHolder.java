package com.ykim.android_mvp_base.ui.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by ykim on 2017. 7. 12..
 */

public abstract class BaseViewHolder<T> extends LinearLayout {

  public BaseViewHolder(Context context) {
    super(context);
    init();
  }

  public BaseViewHolder(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public BaseViewHolder(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public BaseViewHolder(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  private void init() {
    inflate(getContext(),layoutResId(),this);
  }

  protected abstract @LayoutRes int layoutResId();

  abstract void bind(T item);
}
