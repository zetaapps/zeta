package zeta.android.apps.di.module;

import dagger.Module;
import dagger.Provides;
import zeta.android.apps.view.activity.navigation.NavigationFragmentManager;

@Module
public class ActivityModule {

    @Provides
    NavigationFragmentManager providesNavigationFragmentManager() {
        return new NavigationFragmentManager();
    }
}
