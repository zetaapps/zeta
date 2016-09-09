package zeta.android.apps.developer.tools;

import android.content.Context;
import android.os.StrictMode;
import android.util.DisplayMetrics;

import com.codemonkeylabs.fpslibrary.TinyDancer;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.annotation.ParametersAreNonnullByDefault;

import timber.log.Timber;
import zeta.android.apps.ZetaApplication;
import zeta.android.apps.sharedPref.DebugSharedPreferences;
import zeta.android.apps.tools.DeveloperTools;

import static android.view.Gravity.START;
import static android.view.Gravity.TOP;

@ParametersAreNonnullByDefault
public class DeveloperToolsImpl implements DeveloperTools {

    private RefWatcher mRefWatcherInstance;
    private DebugSharedPreferences mDebugSharedPreferences;

    public DeveloperToolsImpl(DebugSharedPreferences preferences) {
        this.mDebugSharedPreferences = preferences;
    }

    @Override
    public void initialize(ZetaApplication application) {
        Context context = application.getApplicationContext();

        Timber.plant(new Timber.DebugTree());

        if (mDebugSharedPreferences.getStrictModeEnabled()) {
            StrictMode.enableDefaults();
        }

        if (mDebugSharedPreferences.getStethoEnabled()) {
            Stetho.initializeWithDefaults(context);
        }

        if (mDebugSharedPreferences.getLeakyCanaryEnabled()) {
            mRefWatcherInstance = LeakCanary.install(application);
        }

        if (mDebugSharedPreferences.getTinyDancerEnabled()) {
            final DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
            TinyDancer.create()
                    .redFlagPercentage(0.2f)
                    .yellowFlagPercentage(0.05f)
                    .startingGravity(TOP | START)
                    .startingXPosition(displayMetrics.widthPixels / 10)
                    .startingYPosition(displayMetrics.heightPixels / 4)
                    .show(application);
        } else {
            try {
                TinyDancer.hide(application);
            } catch (Exception e) {
                // In some cases TinyDancer can not be hidden without exception:
                // for example when you start it first time on Android 6.
                Timber.e(e, "Cannot hide TinyDancer");
            }
        }
    }

    @Override
    public void watchForMemoryLeaks(Object object) {
        if (mRefWatcherInstance == null) {
            return;
        }
        mRefWatcherInstance.watch(object);
    }
}
