package zeta.android.apps.di.component;

import dagger.Subcomponent;
import zeta.android.apps.di.module.DebugPresenterModule;
import zeta.android.apps.di.scope.FragmentScope;
import zeta.android.apps.ui.fragment.DebugFragment;

@FragmentScope
@Subcomponent(modules = {DebugPresenterModule.class})
public interface DebugComponent {

    void inject(DebugFragment fragment);
}
