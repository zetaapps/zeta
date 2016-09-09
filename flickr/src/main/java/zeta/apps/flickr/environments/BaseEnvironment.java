package zeta.apps.flickr.environments;

import android.support.annotation.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

import static zeta.apps.flickr.Flickr.Environment;

@ParametersAreNonnullByDefault
public interface BaseEnvironment {

    @Environment
    int getEnvironment();

    String getBaseUrl();

    @Nullable
    String getKey();

    @Nullable
    String getSecureKey();
}
