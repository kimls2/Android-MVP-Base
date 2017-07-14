package com.ykim.android_mvp_base.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by ykim on 2017. 7. 13..
 */

@SuppressWarnings("SpellCheckingInspection") public class SnackbarUtil {
  public static void showSnackbar(View view, String message) {
    if (view == null || message == null) {
      return;
    }
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
  }
}
