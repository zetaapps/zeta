package zeta.android.apps.developer.tools;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.android.apps.ZetaApplication;
import zeta.android.apps.sharedPref.DebugSharedPreferences;
import zeta.android.apps.tools.DeveloperTools;

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
