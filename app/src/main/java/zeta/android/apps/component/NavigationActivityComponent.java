package zeta.android.apps.component;

import dagger.Subcomponent;
import zeta.android.apps.activities.NavigationActivity;
import zeta.android.apps.dagger.ActivityScope;

@ActivityScope
@Subcomponent
public interface NavigationActivityComponent {

    void inject(NavigationActivity activity);

}