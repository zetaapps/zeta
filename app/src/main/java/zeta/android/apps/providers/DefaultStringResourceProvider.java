package zeta.android.apps.providers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import zeta.android.apps.providers.interfaces.StringResourceProvider;

public class DefaultStringResourceProvider implements StringResourceProvider {

    private Context mContext;

    public DefaultStringResourceProvider(@NonNull final Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public String getStringFromResource(@StringRes final int stringRes) {
        return mContext.getString(stringRes);
    }

    @Override
    public String getStringFromResource(@StringRes final int stringResId, final Object... formatArgs) {
        return mContext.getString(stringResId, formatArgs);
    }

}
