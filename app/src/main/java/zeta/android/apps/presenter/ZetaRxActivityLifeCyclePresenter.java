package zeta.android.apps.presenter;

import android.support.annotation.CallSuper;

import zeta.android.apps.rx.managers.RxSubscriptionManager;
import zeta.android.apps.rx.providers.RxSchedulerProvider;

public abstract class ZetaRxActivityLifeCyclePresenter<Presentation> extends RxSubscriptionManager
        implements ZetaActivityLifeCyclePresenter<Presentation> {

    public ZetaRxActivityLifeCyclePresenter(RxSchedulerProvider schedulerProvider) {
        super(schedulerProvider);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        cancelSubscriptions();
    }
}
