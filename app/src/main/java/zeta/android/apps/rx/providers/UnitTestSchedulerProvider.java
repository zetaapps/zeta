package zeta.android.apps.rx.providers;

import rx.Scheduler;
import rx.schedulers.Schedulers;
import zeta.android.apps.rx.interfaces.RxSchedulerProvider;
import zeta.android.utils.annotation.ExistsForTesting;

@ExistsForTesting
public class UnitTestSchedulerProvider implements RxSchedulerProvider {
    @Override
    public Scheduler schedulerMainThread() {
        return Schedulers.immediate();
    }

    @Override
    public Scheduler schedulerBackgroundThread() {
        return Schedulers.immediate();
    }
}
