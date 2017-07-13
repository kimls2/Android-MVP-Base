package com.ykim.android_mvp_base.data;

import com.ykim.android_mvp_base.data.model.GalleryImage;
import com.ykim.android_mvp_base.data.model.GalleryResponse;
import com.ykim.android_mvp_base.data.remote.ImgurService;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by ykim on 2017. 7. 13..
 */
@RunWith(MockitoJUnitRunner.class) public class DataManagerTest {

  @Mock ImgurService imgurService;
  @InjectMocks DataManager dataManager;

  @Test public void whenResponseSuccess_dataShouldReturn() {
    GalleryResponse response = new GalleryResponse().setSuccess(true);
    List<GalleryImage> galleryImages = new ArrayList<>();
    galleryImages.add(new GalleryImage().setId("1"));
    response.setData(galleryImages);
    Mockito.when(imgurService.getGallery(0)).thenReturn(Observable.just(response));
    TestObserver<List<GalleryImage>> testObserver = new TestObserver<>();
    dataManager.getGallery(0).subscribe(testObserver);
    testObserver.assertValue(response.getData());
    testObserver.assertComplete();
    testObserver.assertNoErrors();
  }

  @Test public void whenResponseFails_dataShouldReturnNull() {
    GalleryResponse response = new GalleryResponse().setSuccess(false);
    Mockito.when(imgurService.getGallery(0)).thenReturn(Observable.just(response));
    TestObserver<List<GalleryImage>> testObserver = new TestObserver<>();
    dataManager.getGallery(0).subscribe(testObserver);
    testObserver.assertNoValues();
  }

  @Test public void whenResponseSuccessAndDataIsNull_shouldBeFiltered() {
    GalleryResponse response = new GalleryResponse().setSuccess(true);
    List<GalleryImage> galleryImages = new ArrayList<>();
    response.setData(galleryImages);
    Mockito.when(imgurService.getGallery(0)).thenReturn(Observable.just(response));
    TestObserver<List<GalleryImage>> testObserver = new TestObserver<>();
    dataManager.getGallery(0).subscribe(testObserver);
    testObserver.assertNoValues();
    testObserver.assertNoErrors();
  }
}