package zeta.android.apps.presenter

import javax.annotation.ParametersAreNonnullByDefault
import javax.inject.Inject

import zeta.android.apps.BuildConfig
import zeta.android.apps.presenter.ZetaRxActivityLifeCyclePresenter
import zeta.android.apps.rx.providers.RxSchedulerProvider
import zeta.android.apps.ui.presentation.NavigationPresentation

@ParametersAreNonnullByDefault
class NavigationPresenter @Inject
constructor(schedulerProvider: RxSchedulerProvider) : ZetaRxActivityLifeCyclePresenter<NavigationPresentation>(schedulerProvider) {

    private var mPresentation: NavigationPresentation? = null

    override fun onCreate(navigationPresentation: NavigationPresentation) {
        mPresentation = navigationPresentation
        mPresentation?.showDebugMenuItem(BuildConfig.DEBUG)
    }

    override fun onPostResume() {
        // no-op
    }

    override fun onPause() {
        // no-op
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresentation = null
    }

    fun onMenuItemHomeSelected() {
        mPresentation?.showBaseScreen()
    }

    fun onMenuItemDebugSelected() {
        mPresentation?.showDebugScreen()
    }
}
