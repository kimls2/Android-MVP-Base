package com.ykim.android_mvp_base.data;

import com.ykim.android_mvp_base.data.model.GalleryImage;
import com.ykim.android_mvp_base.data.model.GalleryResponse;
import com.ykim.android_mvp_base.data.remote.ImgurService;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Created by ykim on 2017. 4. 11..
 */

@Singleton public class DataManager {

  private final ImgurService imgurService;

  @Inject DataManager(ImgurService imgurService) {
    this.imgurService = imgurService;
  }

  public Observable<List<GalleryImage>> getGallery(int page) {
    return imgurService.getGallery(page)
        .filter(response -> response.isSuccess() && CollectionUtils.isNotEmpty(response.getData()))
        .map(GalleryResponse::getData);
  }
}
