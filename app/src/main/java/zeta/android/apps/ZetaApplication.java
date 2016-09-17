package zeta.android.apps;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

import dagger.Lazy;
import rx.plugins.RxJavaHooks;
import zeta.android.apps.di.component.ZetaAppComponent;
import zeta.android.apps.di.module.ZetaAppModule;
import zeta.android.apps.rx.handlers.NetworkConnectivityErrorHandler;
import zeta.android.apps.tools.DeveloperTools;

@ParametersAreNonnullByDefault
public class ZetaApplication extends MultiDexApplication {

    @Inject
    public Lazy<DeveloperTools> mDeveloperTools;

    private ZetaAppComponent mZetaAppComponent;

    public static void watchForMemoryLeaks(Context context, Object object) {
        ZetaApplication application = (ZetaApplication) context.getApplicationContext();
        application.mDeveloperTools.get().watchForMemoryLeaks(object);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final Context context = getApplicationContext();
        mZetaAppComponent = DaggerZetaAppComponent.builder()
                .zetaAppModule(new ZetaAppModule(this, context))
                .build();
        mZetaAppComponent.inject(this);
        mDeveloperTools.get().initialize(this);

        //Just to log the Rx Global errors.
        RxJavaHooks.setOnError(new NetworkConnectivityErrorHandler());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //Do cleans ups
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //Do cleans ups
    }

    //region Dependency Injection
    public ZetaAppComponent getZetaAppComponent() {
        return mZetaAppComponent;
    }
    //endregion
}
