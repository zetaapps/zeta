package zeta.android.apps.tools;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.android.apps.ZetaApplication;

@ParametersAreNonnullByDefault
public interface DeveloperTools {

    /**
     * Initialize all developer tool which is applicable
     *
     * @param application : application
     */
    void initialize(ZetaApplication application);

    /**
     * Use leaky canary to watch for the memory leak.
     *
     * @param object : Object where we need to check for memory leaks
     */
    void watchForMemoryLeaks(Object object);
}
