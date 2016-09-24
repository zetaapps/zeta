package zeta.andriod.apps.di.component;

import dagger.Subcomponent;
import zeta.andriod.apps.di.module.DebugPresenterModule;
import zeta.andriod.apps.ui.fragment.DebugFragment;
import zeta.android.apps.di.scope.FragmentScope;

@FragmentScope
@Subcomponent(modules = {DebugPresenterModule.class})
public interface DebugComponent {

    void inject(DebugFragment fragment);
}
