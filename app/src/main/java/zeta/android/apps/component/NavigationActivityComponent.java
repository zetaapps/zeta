package zeta.android.apps.component;

import dagger.Subcomponent;
import zeta.android.apps.activities.NavigationActivity;
import zeta.android.apps.dagger.ActivityScope;
import zeta.android.apps.modules.ActivityModule;

@ActivityScope
@Subcomponent(modules = {
        ActivityModule.class
})
public interface NavigationActivityComponent {

    void inject(NavigationActivity activity);

}