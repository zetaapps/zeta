package zeta.android.apps.fragments.common.presenter;

import android.support.annotation.CallSuper;

import zeta.android.apps.rx.RxSubscriptionManager;
import zeta.android.apps.rx.interfaces.RxSchedulerProvider;

public abstract class RxFragmentLifeCyclePresenter<Presentation> extends RxSubscriptionManager
        implements FragmentLifeCyclePresenter<Presentation> {

    public RxFragmentLifeCyclePresenter(RxSchedulerProvider schedulerProvider) {
        super(schedulerProvider);
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        cancelSubscriptions();
    }

    @Override
    public void onDestroy() {
        // no-op
    }
}
