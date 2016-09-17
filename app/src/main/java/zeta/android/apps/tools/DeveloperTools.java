package zeta.android.apps.tools;

import javax.annotation.ParametersAreNonnullByDefault;

import zeta.android.apps.ZetaApplication;

/**
 * Initializer for developer tools which are useful in local builds, but implementers may decide to skip
 * based on the build type.  May also be used to provide a no-op initializer for production builds.
 */
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
