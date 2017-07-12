package com.ykim.android_mvp_base.di;

import com.ykim.android_mvp_base.data.remote.ImgurService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by ykim on 2017. 7. 12..
 */

@Module class AppModule {
  @Singleton @Provides ImgurService provideImgurService() {
    return ImgurService.Factory.makeImugurService();
  }
}
