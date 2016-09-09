package zeta.android.apps.developer.debug.subcomponents;

import dagger.Subcomponent;
import zeta.android.apps.dagger.FragmentScope;
import zeta.android.apps.developer.debug.DebugFragment;
import zeta.android.apps.developer.debug.modules.DebugPresenterModule;

@FragmentScope
@Subcomponent(modules = {DebugPresenterModule.class})
public interface DebugComponent {

    void inject(DebugFragment fragment);
}
