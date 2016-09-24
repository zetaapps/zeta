package zeta.android.apps.presenter;

import android.view.Menu;
import android.view.MenuInflater;

import com.github.pedrovgs.lynx.LynxConfig;

import java.util.concurrent.TimeUnit;

import javax.annotation.ParametersAreNonnullByDefault;

import okhttp3.logging.HttpLoggingInterceptor;
import zeta.android.apps.R;
import zeta.android.apps.ui.inteface.DebugPresentation;
import zeta.android.apps.rx.providers.RxSchedulerProvider;
import zeta.android.apps.sharedPref.DebugSharedPreferences;

@ParametersAreNonnullByDefault
public class DebugPresenter extends ZetaRxFragmentLifeCyclePresenter<DebugPresentation> {

    private DebugPresentation mPresentation;

    private LynxConfig mLynxConfig;
    private DebugSharedPreferences mDebugSharedPreferences;

    public DebugPresenter(RxSchedulerProvider schedulerProvider,
                          DebugSharedPreferences debugSharedPreferences,
                          LynxConfig lynxConfig) {
        super(schedulerProvider);
        mLynxConfig = lynxConfig;
        mDebugSharedPreferences = debugSharedPreferences;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //todo::
    }

    @Override
    public void onCreateView(DebugPresentation homePresentation) {
        mPresentation = homePresentation;
        mPresentation.initLoggerSpinnerAdapter(getLoggingSpinnerAdapter());
    }

    @Override
    public void onViewCreated() {
        initDeveloperInspectionTools();
        initLoggingTools();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresentation = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDebugSharedPreferences = null;
    }

    public void setSethoEnabled(boolean sethoEnabled) {
        if (sethoEnabled == mDebugSharedPreferences.getStethoEnabled()) {
            return;
        }
        mDebugSharedPreferences.saveStethoEnabled(sethoEnabled);
        mPresentation.setEnableInspectionToolsApplyButton(true);
    }

    public void setStrictModeEnabled(boolean strictModeEnabled) {
        if (strictModeEnabled == mDebugSharedPreferences.getStrictModeEnabled()) {
            return;
        }
        mDebugSharedPreferences.saveStrictModeEnabled(strictModeEnabled);
        mPresentation.setEnableInspectionToolsApplyButton(true);
    }

    public void setTinyDancerEnabled(boolean tinyDancerEnabled) {
        if (tinyDancerEnabled == mDebugSharedPreferences.getTinyDancerEnabled()) {
            return;
        }
        mDebugSharedPreferences.saveTinyDancerEnabled(tinyDancerEnabled);
        mPresentation.setEnableInspectionToolsApplyButton(true);
    }

    public void setLeakyCanaryEnabled(boolean leakyCanaryEnabled) {
        if (leakyCanaryEnabled == mDebugSharedPreferences.getLeakyCanaryEnabled()) {
            return;
        }
        mDebugSharedPreferences.saveLeakyCanaryEnabled(leakyCanaryEnabled);
        mPresentation.setEnableInspectionToolsApplyButton(true);
    }

    public void applyInspectionToolsSettings() {
        mPresentation.showToastMessage(R.string.debug_will_restart);
        mPresentation.restartApp(TimeUnit.MILLISECONDS.toMillis(2500));
    }

    public void applyLoggingSettings() {
        mPresentation.showToastMessage(R.string.debug_will_restart);
        mPresentation.restartApp(TimeUnit.MILLISECONDS.toMillis(2500));
    }

    public void showLoggingConsole() {
        mPresentation.showLoggingConsole(mLynxConfig);
    }

    public void setLoggingLevelSelected(int position) {
        HttpLoggingInterceptor.Level level = mDebugSharedPreferences.getHttpLoggingLevel();
        if (level.ordinal() == position) {
            return;
        }
        mDebugSharedPreferences.saveHttpLoggingLevel(HttpLoggingInterceptor.Level.values()[position]);
        mPresentation.setEnableLoggingToolsApplyButton(true);
    }

    private void initDeveloperInspectionTools() {
        mPresentation.setEnableStetho(mDebugSharedPreferences.getStethoEnabled());
        mPresentation.setEnableStrictMode(mDebugSharedPreferences.getStrictModeEnabled());
        mPresentation.setEnableTinyDancer(mDebugSharedPreferences.getTinyDancerEnabled());
        mPresentation.setEnableLeakyCanary(mDebugSharedPreferences.getLeakyCanaryEnabled());
        mPresentation.setEnableInspectionToolsApplyButton(false);
    }

    private void initLoggingTools() {
        final int position = mDebugSharedPreferences.getHttpLoggingLevel().ordinal();
        mPresentation.setLoggerSpinnerSelected(position);
        mPresentation.setEnableLoggingToolsApplyButton(false);
    }

    private String[] getLoggingSpinnerAdapter() {
        int size = HttpLoggingInterceptor.Level.values().length;
        String[] spinnerItems = new String[size];
        for (int i = 0; i < size; i++) {
            spinnerItems[i] = HttpLoggingInterceptor.Level.values()[i].name();
        }
        return spinnerItems;
    }

}
