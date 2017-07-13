package com.ykim.android_mvp_base.ui.main;

import com.ykim.android_mvp_base.data.model.GalleryImage;
import com.ykim.android_mvp_base.ui.base.BaseMvp;
import java.util.List;

/**
 * Created by ykim on 2017. 7. 12..
 */

public interface MainMvp {

  interface View extends BaseMvp.View {
    void showImages(List<GalleryImage> images);
  }

  interface Presenter {
    void loadImages(int page);
  }
}
