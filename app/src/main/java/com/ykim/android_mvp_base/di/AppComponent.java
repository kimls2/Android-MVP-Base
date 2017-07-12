package com.ykim.android_mvp_base.di;

import android.app.Application;
import com.ykim.android_mvp_base.MyApp;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

/**
 * Created by ykim on 2017. 7. 12..
 */

@Singleton
@Component(modules = { AppModule.class, AndroidInjectionModule.class, ActivityModule.class })
interface AppComponent {
  @Component.Builder interface Builder {
    @BindsInstance Builder app(Application application);

    AppComponent build();
  }

  void inject(MyApp app);
}
