package zeta.android.apps.rx;

import rx.Scheduler;
import rx.schedulers.Schedulers;
import zeta.android.apps.qualifiers.ExistsForTesting;
import zeta.android.apps.rx.interfaces.RxSchedulerProvider;

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
