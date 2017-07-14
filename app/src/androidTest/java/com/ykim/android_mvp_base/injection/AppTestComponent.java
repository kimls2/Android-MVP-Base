package com.ykim.android_mvp_base.injection;

import com.ykim.android_mvp_base.data.DataManager;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by ykim on 2017. 7. 14..
 */

@Singleton @Component(modules = AppTestModule.class) public interface AppTestComponent {
  DataManager dataManager();
}
