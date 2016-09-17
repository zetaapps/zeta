package zeta.android.apps.di.component;

import javax.inject.Singleton;

import dagger.Component;
import zeta.android.apps.ZetaApplication;
import zeta.android.apps.di.module.EventBusModule;
import zeta.android.apps.di.module.NetworkModule;
import zeta.android.apps.di.module.ZetaAppModule;
import zeta.android.apps.di.module.DebugModule;
import zeta.android.apps.di.module.EventBusNoSubscriberModule;
import zeta.android.apps.di.module.OkHttpInterceptorsModule;

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
