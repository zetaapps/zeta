package zeta.android.apps.modules;

import dagger.Module;
import dagger.Provides;
import zeta.android.apps.activities.managers.NavigationFragmentManager;

@Module
public class ActivityModule {

    @Provides
    NavigationFragmentManager providesNavigationFragmentManager() {
        return new NavigationFragmentManager();
    }
}
