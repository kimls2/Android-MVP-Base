package com.ykim.android_mvp_base.injection;

import com.ykim.android_mvp_base.data.DataManager;
import com.ykim.android_mvp_base.data.remote.ImgurService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static org.mockito.Mockito.mock;

/**
 * Created by ykim on 2017. 7. 14..
 */

@Module public class AppTestModule {

  @Singleton @Provides ImgurService provideImgurService() {
    return mock(ImgurService.class);
  }

  @Singleton @Provides DataManager provideDataManager() {
    return mock(DataManager.class);
  }
}
