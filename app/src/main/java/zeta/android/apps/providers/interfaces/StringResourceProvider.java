package zeta.android.apps.providers.interfaces;

import android.support.annotation.StringRes;

/**
 * A source of string resources via their String Resource ids, without making direct reference to {@link android.content.Context}.
 */
public interface StringResourceProvider {

    String getStringFromResource(@StringRes final int stringRes);

    String getStringFromResource(@StringRes int stringResId, Object... formatArgs);

}
