package zeta.android.apps.presenter;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.inject.Inject;

import zeta.android.apps.BuildConfig;
import zeta.android.apps.rx.providers.RxSchedulerProvider;
import zeta.android.apps.ui.presentation.NavigationPresentation;

@ParametersAreNonnullByDefault
public class NavigationPresenter extends ZetaRxActivityLifeCyclePresenter<NavigationPresentation> {

    private NavigationPresentation mPresentation;

    @Inject
    public NavigationPresenter(RxSchedulerProvider schedulerProvider) {
        super(schedulerProvider);
    }

    @Override
    public void onCreate(NavigationPresentation navigationPresentation) {
        mPresentation = navigationPresentation;
        mPresentation.showDebugMenuItem(BuildConfig.DEBUG);
    }

    @Override
    public void onPostResume() {
        // no-op
    }

    @Override
    public void onPause() {
        // no-op
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresentation = null;
    }

    public void onMenuItemHomeSelected() {
        mPresentation.showBaseScreen();
    }

    public void onMenuItemDebugSelected() {
        mPresentation.showDebugScreen();
    }
}
