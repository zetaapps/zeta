package zeta.android.apps.di.component;

import dagger.Subcomponent;
import zeta.android.apps.di.module.DebugPresenterModule;
import zeta.android.apps.ui.fragment.DebugFragment;
import zeta.android.apps.di.scope.FragmentScope;

@FragmentScope
@Subcomponent(modules = {DebugPresenterModule.class})
public interface DebugComponent {

    void inject(DebugFragment fragment);
}
