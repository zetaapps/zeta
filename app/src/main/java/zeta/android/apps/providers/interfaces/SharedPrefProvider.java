package zeta.android.apps.providers.interfaces;

import android.support.annotation.Nullable;

public interface SharedPrefProvider {

    @Nullable
    String getBaseAuth();

    void setBaseAuth(@Nullable String baseAuth);

}
