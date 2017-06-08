package zeta.android.apps.di.component;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import zeta.android.apps.di.module.ActivityModule;
import zeta.android.apps.di.scope.ActivityScope;
import zeta.android.apps.ui.activity.NavigationActivity;

@ActivityScope
@Subcomponent(modules = {
        ActivityModule.class
})
public interface NavigationActivityComponent extends AndroidInjector<NavigationActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<NavigationActivity> {}

}