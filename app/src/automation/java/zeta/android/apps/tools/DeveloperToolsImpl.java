package zeta.android.apps.tools;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.android.apps.ZetaApplication;
import zeta.android.apps.sharedPref.DebugSharedPreferences;

@ParametersAreNonnullByDefault
public class DeveloperToolsImpl implements DeveloperTools {

    public DeveloperToolsImpl(DebugSharedPreferences preferences) {
        //no ops
    }

    @Override
    public void initialize(ZetaApplication application) {
        //No ops
    }

    @Override
    public void watchForMemoryLeaks(Object object) {
        //No ops
    }
}
