package zeta.android.apps.providers;

import android.content.Context;
import android.content.SharedPreferences;

import zeta.android.apps.providers.interfaces.SharedPrefProvider;

public class DefaultSharedPrefProvider implements SharedPrefProvider {
    private static final String TAG = DefaultSharedPrefProvider.class.getSimpleName();
    private static final String KEY_ZETA_PREFS = TAG + "zetaSharedPrefs";

    private Context mContext;

    public DefaultSharedPrefProvider(Context context) {
        mContext = context;
    }

    private SharedPreferences getZetaPrefs() {
        return mContext.getSharedPreferences(KEY_ZETA_PREFS, Context.MODE_PRIVATE);
    }

}
