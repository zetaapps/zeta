package zeta.android.apps.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import zeta.android.apps.providers.interfaces.SharedPrefProvider;
import zeta.android.apps.qualifiers.Hack;
import zeta.android.apps.qualifiers.HackType;

@Hack(explanation = "Basic auth for the demo purpose only", hackType = HackType.FOR_DEMO_ONLY)
public class DefaultSharedPrefProvider implements SharedPrefProvider {

    private static final String TAG = DefaultSharedPrefProvider.class.getSimpleName();
    private static final String KEY_BASE_AUTH = "baseAuth";
    private static final String KEY_ZETA_PREFS = TAG + "zetaSharedPrefs";

    private Context mContext;

    public DefaultSharedPrefProvider(Context context) {
        mContext = context;
    }

    private SharedPreferences getZetaPrefs() {
        return mContext.getSharedPreferences(KEY_ZETA_PREFS, Context.MODE_PRIVATE);
    }

    @Override
    @Nullable
    public String getBaseAuth() {
        return getZetaPrefs().getString(KEY_BASE_AUTH, null);
    }

    @Override
    public void setBaseAuth(String baseAuth) {
        getZetaPrefs().edit()
                .putString(KEY_BASE_AUTH, baseAuth)
                .apply();
    }

}
