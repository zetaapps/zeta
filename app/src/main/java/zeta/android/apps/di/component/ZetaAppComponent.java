package zeta.android.apps.di.component;

import javax.inject.Singleton;

import dagger.Component;
import zeta.andriod.apps.di.component.DebugComponent;
import zeta.andriod.apps.di.module.DebugModule;
import zeta.andriod.apps.di.module.EventBusNoSubscriberModule;
import zeta.andriod.apps.di.module.OkHttpInterceptorsModule;
import zeta.android.apps.ZetaApplication;
import zeta.android.apps.di.module.EventBusModule;
import zeta.android.apps.di.module.NetworkModule;
import zeta.android.apps.di.module.ZetaAppModule;

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
