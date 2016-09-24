package zeta.android.apps.ui.inteface;

import android.support.annotation.StringRes;

import com.github.pedrovgs.lynx.LynxConfig;

public interface DebugPresentation {

    void initLoggerSpinnerAdapter(String[] loggerSpinnerData);

    void setLoggerSpinnerSelected(int position);

    void setEnableLoggingToolsApplyButton(boolean enableLoggingToolsApplyButton);

    void setEnableStetho(boolean enableStetho);

    void setEnableStrictMode(boolean enableStrictMode);

    void setEnableTinyDancer(boolean enableTinyDancer);

    void setEnableLeakyCanary(boolean enableLeakyCanary);

    void setEnableInspectionToolsApplyButton(boolean enableInspectionToolsApplyButton);

    void showToastMessage(@StringRes int message);

    void restartApp(long delay);

    void showLoggingConsole(LynxConfig config);

}
