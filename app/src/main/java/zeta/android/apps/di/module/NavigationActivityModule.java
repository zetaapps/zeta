package zeta.android.apps.di.module;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import zeta.android.apps.di.component.NavigationActivityComponent;
import zeta.android.apps.ui.activity.NavigationActivity;

@Module(subcomponents = NavigationActivityComponent.class)
public abstract class NavigationActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(NavigationActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindsNavActivity(NavigationActivityComponent.Builder builder);
}
