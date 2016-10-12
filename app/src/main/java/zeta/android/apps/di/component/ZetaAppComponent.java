package zeta.android.apps.di.component;

import javax.inject.Singleton;

import dagger.Component;
import zeta.android.apps.di.module.*;
import zeta.android.apps.ZetaApplication;

@Singleton
@Component(modules = {
        DebugModule.class,
        NetworkModule.class,
        EventBusModule.class,
        ZetaAppModule.class,
        OkHttpInterceptorsModule.class,
        EventBusNoSubscriberModule.class})
public interface ZetaAppComponent {

    NavigationActivityComponent navigationActivity();

    DebugComponent debugComponent();

    void inject(ZetaApplication targetApplication);

}
