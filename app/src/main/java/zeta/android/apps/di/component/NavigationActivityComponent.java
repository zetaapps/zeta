package zeta.android.apps.di.component;

import dagger.Subcomponent;
import zeta.android.apps.view.activity.NavigationActivity;
import zeta.android.apps.di.module.ActivityModule;
import zeta.android.apps.di.scope.ActivityScope;

@ActivityScope
@Subcomponent(modules = {
        ActivityModule.class
})
public interface NavigationActivityComponent {

    void inject(NavigationActivity activity);

}