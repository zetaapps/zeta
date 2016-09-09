package zeta.android.apps;

import javax.inject.Singleton;

import dagger.Component;
import zeta.android.apps.component.NavigationActivityComponent;
import zeta.android.apps.developer.debug.subcomponents.DebugComponent;
import zeta.android.apps.fragments.subcomponents.ZetaComponent;
import zeta.android.apps.modules.ConfigModule;
import zeta.android.apps.modules.DebugModule;
import zeta.android.apps.modules.EventBusModule;
import zeta.android.apps.modules.EventBusNoSubscriberModule;
import zeta.android.apps.modules.NetworkModule;
import zeta.android.apps.modules.OkHttpInterceptorsModule;
import zeta.android.apps.modules.ZetaAppModule;
import zeta.apps.flickr.FlickrModule;
import zeta.apps.flickr.modules.FlickrImageModule;

@Singleton
@Component(modules = {
        DebugModule.class,
        NetworkModule.class,
        EventBusModule.class,
        ZetaAppModule.class,
        ConfigModule.class,
        FlickrModule.class,
        OkHttpInterceptorsModule.class,
        EventBusNoSubscriberModule.class})
public interface ZetaAppComponent {

    NavigationActivityComponent navigationActivity();

    ZetaComponent zetaComponent(FlickrImageModule flickrImageModule);

    DebugComponent debugComponent();

    void inject(ZetaApplication targetApplication);

}
