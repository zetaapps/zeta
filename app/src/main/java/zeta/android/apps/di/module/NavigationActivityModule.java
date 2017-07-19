package zeta.android.apps.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import zeta.android.apps.di.scope.ActivityScope;
import zeta.android.apps.ui.activity.NavigationActivity;

@Module
public abstract class NavigationActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {ActivityModule.class})
    abstract NavigationActivity contributeNavigationActivityInjector();
}
