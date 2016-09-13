package zeta.android.apps.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zeta.android.apps.providers.DefaultConnectivityProvider;
import zeta.android.apps.providers.DefaultSharedPrefProvider;
import zeta.android.apps.providers.DefaultStringResourceProvider;
import zeta.android.apps.providers.interfaces.ConnectivityProvider;
import zeta.android.apps.providers.interfaces.SharedPrefProvider;
import zeta.android.apps.providers.interfaces.StringResourceProvider;
import zeta.android.apps.rx.interfaces.RxSchedulerProvider;
import zeta.android.apps.rx.managers.RxSubscriptionManager;
import zeta.android.apps.rx.providers.PresenterSchedulerProvider;

@Module
@Singleton
public class ZetaAppModule {

    private final Context appContext;
    private final Application application;

    public ZetaAppModule(Application application, Context appContext) {
        this.appContext = appContext;
        this.application = application;
    }

    @Provides
    Application providesApplication() {
        return application;
    }

    @Provides
    Context providesAppContext() {
        return appContext;
    }

    @Provides
    StringResourceProvider providesStringResourceProvider(Context context) {
        return new DefaultStringResourceProvider(context);
    }

    @Provides
    SharedPrefProvider providesSharedPrefProvider(Context context) {
        return new DefaultSharedPrefProvider(context);
    }

    @Provides
    ConnectivityProvider providesConnectivityProvider(Context context) {
        return new DefaultConnectivityProvider(context);
    }

    @Provides
    RxSubscriptionManager providesRxSubscriptionManager(RxSchedulerProvider rxSchedulerProvider) {
        return new RxSubscriptionManager(rxSchedulerProvider);
    }

    @Provides
    RxSchedulerProvider providesRxSchedulerProvider() {
        return new PresenterSchedulerProvider();
    }

}
