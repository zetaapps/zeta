package zeta.android.apps.providers.interfaces;

import android.support.annotation.StringRes;

public interface StringResourceProvider {

    String getStringFromResource(@StringRes final int stringRes);

    String getStringFromResource(@StringRes int stringResId, Object... formatArgs);

}
