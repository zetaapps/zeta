package zeta.android.apps;

import javax.inject.Singleton;

import dagger.Component;
import zeta.android.apps.component.NavigationActivityComponent;
import zeta.android.apps.developer.debug.subcomponents.DebugComponent;
import zeta.android.apps.modules.DebugModule;
import zeta.android.apps.modules.EventBusModule;
import zeta.android.apps.modules.EventBusNoSubscriberModule;
import zeta.android.apps.modules.NetworkModule;
import zeta.android.apps.modules.OkHttpInterceptorsModule;
import zeta.android.apps.modules.ZetaAppModule;

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
